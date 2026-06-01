-------------------------------------------------------
1. area
-------------------------------------------------------
Atributos:
  - id              integer         PK
  - nombre          varchar
  - descripcion     varchar
  - activo          boolean

-------------------------------------------------------
2. cargo
-------------------------------------------------------
Atributos
	-id 			integer 	PK
	-id_area		integer		FK
	-nombre 		varchar
	-descripcion 	varchar	
	-activo 		boolean
	
Relaciones
	-id_area -> areas(id)
-------------------------------------------------------
3. Bancos
-------------------------------------------------------
Atributos
	-id 			integer 	PK
	-nombre 		varchar
-------------------------------------------------------
4. regimen_pension
-------------------------------------------------------
Atributos
	-id 			integer 	PK
	-nombre 		varchar
-------------------------------------------------------
5. tipo_AFP
-------------------------------------------------------
Atributos
	-id 			integer 	PK
	-id_regimen		integer		FK
	-nombre 		varchar
Relaciones
	-id_area -> regimen_pension(id)
Descripcion
	-Solo si es AFP
-------------------------------------------------------
6. comisiones_APF
-------------------------------------------------------
Atributos
	-id 					integer 	PK
	-afp_id					integer		FK
	-perido 				varchar
	-aporte_obligatorio 	decimal 
	-comision_sobre_ra	 	decimal
	-prima_seguro		 	decimal
	-comision_mixta		 	decimal
Relaciones
	-afp_id -> tipo_AFP(id) 
-------------------------------------------------------
7. Aportaciones
-------------------------------------------------------
Atributos
	-id 					integer 	PK
	-nombre					varchar
	-canitdad 				decimal
Relaciones
	-afp_id -> tipo_AFP(id)
-------------------------------------------------------
8. usuarios 
-------------------------------------------------------
Atributos:
  - id              integer         PK
  - empleado_id     integer
  - email           varchar         
  - password_hash   varchar
  - id_rol          integer			FK
  - id_area         integer			FK
  - ultimo_acceso   timestamptz
  - created_at      timestamptz
  - update_at		timestamptz

Relaciones: (ninguna - tabla raíz)

-------------------------------------------------------
8. estado_contrato
-------------------------------------------------------
Atributos:
  - id              integer         PK
  - nombre          varchar
-------------------------------------------------------
9. contratos
-------------------------------------------------------
Atributos:
  - id              integer         PK
  - id_estado 		integer			FK
  - empleado_id     integer         FK
  - tipo_modalidad  varchar
  - fecha_inicio    date
  - fecha_fin       date 
  - renovado        boolean
  - observacion     text
  - created_at      timestamptz

Relaciones:
  - empleado_id → empleados(id)
  - id_estado -> estado_contrato(id)

-------------------------------------------------------
10. audit_log
-------------------------------------------------------
Atributos:
  - id                integer         PK
  - usuario_id        integer         FK
  - accion            varchar
  - tabla_afectada    varchar
  - registro_id       integer
  - datos_anteriores  jsonb
  - datos_nuevos      jsonb
  - ip_origen         inet
  - created_at        timestamptz

Relaciones:
  - usuario_id → usuarios(id)
-------------------------------------------------------
11. tareas_asistente
-------------------------------------------------------
Atributos:
  - id              integer         PK
  - asignado_a      integer         FK
  - asignado_por    integer         FK
  - titulo          varchar
  - descripcion     text
  - fecha_entrega   date
  - estado          Enum('pendiente','revision','aprobado')
  - anotaciones     text
  - created_at      timestamptz

Relaciones:
  - asignado_a  → usuarios(id)
  - asignado_por → usuarios(id)
-------------------------------------------------------
12. anotacion_tareas
-------------------------------------------------------
Atributos:
  - id              integer         PK
  - asignado_por    integer         FK
  - descripcion     text
  - created_at      timestamptz
Relaciones:
  - asignado_por → usuarios(id)
Descripcion:
Solo puede ser usada por el contador
-------------------------------------------------------
13. estado_empleado
-------------------------------------------------------
Atributos:
  - id              integer         PK
  -descripcion 		varchar
-------------------------------------------------------
14. tipo_documento
-------------------------------------------------------
Atributos:
  - id              integer         PK
  - tipoDocumento 	varchar
-------------------------------------------------------
15. Dato_Financiero
-------------------------------------------------------
Atributos:
  - id              		integer         PK
  - id_regimen				integer 		FK
  - id_tipoAFP				integer			FK
  - id_banco				integer			FK
  - cuenta_bancaria			varchar
  - sueldo_basico			decimal
  - cussp					varchar
  - tipo_comision			
  - nro_cuneta_sueldo		
  - cci
  - banco_cts
  - nro_cuenta_cts 
Relaciones:
  - id_regimen -> regimen_pension(id)
  - id_tipoAFP -> tipo_AFP(id)
  - id_banco   -> Bancos(id)
------------------------------------------------------
16.empleados
-------------------------------------------------------
Atributos:
  - id                    integer       PK
  - cargo_id              integer       FK
  - area_id               integer       FK
  - documento_id          integer       FK
  - nombre			      varchar
  - apellido			  varchar
  - fecha_nacimiento      date
  - fecha_inicio          date
  - fecha_cese            date
  - afp_fecha_filiacion   date
  - asig_familiar         boolean
  - activo                boolean
  - created_at            timestamptz
  - update_at			  timestamptz
Relaciones:
  - cargo_id   → cargos(id)
  - area_id    → areas(id)

