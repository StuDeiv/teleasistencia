from django.contrib import admin

from .models import *

# Register your models here.

admin.site.register(Tipo_Agenda)
admin.site.register(Direccion)
admin.site.register(Clasificacion_Alarma)
admin.site.register(Tipo_Alarma)
admin.site.register(Tipo_Recurso_Comunitario)
admin.site.register(Recurso_Comunitario)
admin.site.register(Persona)
admin.site.register(Tipo_Centro_Sanitario)
admin.site.register(Centro_Sanitario)
admin.site.register(Tipo_Modalidad_Paciente)
admin.site.register(Paciente)
admin.site.register(Relacion_Paciente_Persona)
admin.site.register(Relacion_Paciente_Centro)
admin.site.register(Tipo_Vivienda)
admin.site.register(Terminal)
admin.site.register(Relacion_Terminal_Recurso_Comunitario)
admin.site.register(Agenda)
admin.site.register(Historico_Agenda_Llamadas)
admin.site.register(Dispositivos_Auxiliares_En_Terminal )
admin.site.register(Alarma)
admin.site.register(Centro_Sanitario_En_Alarma)
admin.site.register(Persona_Contacto_En_Alarma)
admin.site.register(Recursos_Comunitarios_En_Alarma)
admin.site.register(Tipo_Situacion)
admin.site.register(Historico_Tipo_Situacion)
admin.site.register(Relacion_Usuario_Centro)


