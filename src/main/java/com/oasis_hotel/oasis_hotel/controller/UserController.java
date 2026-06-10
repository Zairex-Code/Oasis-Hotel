package com.oasis_hotel.oasis_hotel.controller;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oasis_hotel.oasis_hotel.dto.user.UserRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserResponseDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserRoleRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserSetPasswordRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserUpdateRequestDTO;
import com.oasis_hotel.oasis_hotel.entity.enums.Role;
import com.oasis_hotel.oasis_hotel.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST Controller responsible for managing user accounts profiles, adn administrative privileges.
 * Implements granular Role-Based Access Color (RBAC) and safeguards against horizontal/vertical
 * privileges escalation vectors (IDOR) via dynamic Spring Expression Language (SpEL)
 * @author Zairex-Code / Oasis Hotel Tech team
 * @version 1.1
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/users")
public class UserController {
    private final UserService userService;

    /**
     * Extract a paginated list of all users registered within the ecosystem
     * * SECURITY CONTEXT: Restricted to administrative staff only. Huels or common guest (CUSTOMERS)
     * must never have visibility over global user registries to maintain data compliance (e.g., GDPR).
     * 
     * @param pageable pageable Pagination and sorting criteria metadata
     * @return A paginated list of UserResponseDTO wrapped in a ResponseEntity
     */

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAllUser(@PageableDefault Pageable pageable) {
        Page<UserResponseDTO> response = userService.getAllUsers(pageable);
        return ResponseEntity.ok(response);
    }


    /**
     * Retrieves the individual profile details of a single user account matching the provided path  ID
     * * SECURITY CONTEXT (Anti-IDOR Shield): Access is governed by a logical OR condition evaluated at runTime.
     * 1. If the calling entity possess the 'ADMIN  ' role, the ad-hoc clearance bypasses further matching.
     * 2. If the entity is a regular 'CUSTOMER' , their authenticated session user ID (principal.id)
     * MUST evaluate as exactly equal to the target path parameter (#id).
     * This thoroughly  blocks enumeration scripts trying to scrap arbitrary customer information cards
     * 
     * @param id the target database identifier of the user profile
     * @return the found UserResponseDTO wrapped in a ResponseEntity
     */


    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }


    /**
     * Performs a partial text search (wildcard lookup) to discover user rows by name criteria.
     * * SECURITY CONTEXT: Administrative audit tool. Common guests are explicitly prevented from scanning
     * database indexes or searching profiles belonging to other hotel customers
     * 
     * @param name text query string used to match first or last name columns.
     * @param pageable pageable Pagination configuration defaulting to alphabetical layout by 'firstName'
     * @return A matching slice of user profiles.
     */
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search/name")
    public ResponseEntity<Page<UserResponseDTO>> getUserByName(@RequestParam String name, @PageableDefault(size = 10, page = 0, sort = "firstName") Pageable pageable) {
        Page<UserResponseDTO> response = userService.getUserByName(name, pageable);

        return ResponseEntity.ok(response);
    }


    /**
     * Execute a strict unique lookup on user accounts leveraging their email coordinate.
     * * SECURITY CONTEXT: Restricted endpoint for administrative background audits.
     * Prevents regular users from harvesting emails or validating account existences within the network
     * 
     * @param email The target unique string email address.
     * @return the associate UserResponseDTO profile card.
     * 
     */

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        UserResponseDTO response = userService.getUserByEmail(email);
        return ResponseEntity.ok(response);
    }



    /**
     * Segments and partitions user accounts globally using their explicit authorization Roles.
     * * SECURITY CONTEXT: High-privilege metric tool useful for counting total ADMIN accounts
     * or exporting customer segmentation. Completely hidden from the public and regular customers.
     * 
     * @param role The authorization Enum tier to filter (ADMIN, CUSTOMER)
     * @param pageable Pagination metadata tracking table splits.
     * @return Paged collection of users possessing the requested Role metadata. 
     * 
     */

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search/role")
    public ResponseEntity<Page<UserResponseDTO>> getUserByRole(@RequestParam Role role, @PageableDefault(size = 10, page = 0,sort = "id") Pageable pageable) {
        Page<UserResponseDTO> response = userService.getUsersByRole(role, pageable);
        return ResponseEntity.ok(response);
    }


    /**
     * public onboarding endpoint enabling anonymous consumers to create account rows (Guest Registration)
     * *SECURITY CONTEXT: Intentionally omitted from method-level PreAuthorize controls.
     * public clearance rule is managed globally at the WebSecurity filter chain via 'permitAll()' matchers,
     * allowing self-registration before any cryptographic token exists
     * 
     * @param request Validated object body containing mandatory registration entries.
     * @return The freshly created profile card returned with a 201 Created status payload.
     * 
     */

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO request) {
        UserResponseDTO newUser = userService.createUser(request);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


    /**
     * Mutates existing account records to perform updates on name, address, or basic user fields.
     * * SECURITY CONTEXT (Anti-IDOR Shield): Protects account integrity. Prevents standard guests from 
     * injecting data modifications onto neighboring accounts. Only the profile owner (#id matching token principal)
     * or an overacting ADMIN can successfully resolve this route execution block.
     * 
     * @param id The target record identifier to modify
     * @param request The validated update request body wrapper
     * @return The modified UserResponseDTO profile mirror
     */

    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #id")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,@Valid @RequestBody UserUpdateRequestDTO request) {
        UserResponseDTO userUpdated = userService.updateUser(id, request);

        return ResponseEntity.ok(userUpdated);
    }


    /**
     * Modifies the operational system credential (password hash) assigned to a given account.
     * * SECURITY CONTEXT (Strict Principle of least Privilege): Following architectural review,
     * this endpoint enforces an absolute identity boundary by omitting 'hasRole(ADMIN)'
     * Even System administrators are barred from forcing arbitrary password values onto other accounts,
     * effectively neutralizing Rogue Admin hijacking vectors. Password resets must remain 100% self-service
     * based entirely on ownership match (token id == path variable #id).
     * 
     * @param id The target database user account identifier
     * @param request request Validated payload enclosing the new perspective password string
     * @return The corresponding user record summary confirming successful cryptographic hash assignment
     */
    @PreAuthorize("authentication.principal.id == #id")
    @PutMapping("/password/{id}")
    public ResponseEntity<UserResponseDTO> setUserPassword(@PathVariable Long id,@Valid @RequestBody  UserSetPasswordRequestDTO request) {
        UserResponseDTO userPasswordUpdated = userService.setUSerPassword(id, request);

        return ResponseEntity.ok(userPasswordUpdated);
        
    }


    /**
     * Critical Administrative operation designed to modify and escalate authority levels (Roles) in the database
     * *SECURITY CONTEXT: Strict RBAC enforcement. Acts as a vital barrier against vertical privilege
     * escalation exploits. Only an active ADMIN can ever access this route to promote ot demote accounts
     * ensuring regular CUSTOMER entities cannot mutate their roles to acquire admin tokens,
     * 
     * @param id The User identifier whose authorization tier is being modified.
     * @param request request Payload carrying the target role metadata target
     * @return the updated user record displaying the revised security role.
     */

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/set-role/{id}")
    public ResponseEntity<UserResponseDTO> setUserRole(@PathVariable Long id, @RequestBody UserRoleRequestDTO request) {
        UserResponseDTO response = userService.setUserRole(id, request);
        
        return ResponseEntity.ok(response);
    }
    


    
}
