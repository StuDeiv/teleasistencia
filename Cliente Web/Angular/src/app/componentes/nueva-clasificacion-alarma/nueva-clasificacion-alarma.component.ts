import {Component, OnInit} from '@angular/core';
import {IClasificacionAlarma} from '../../interfaces/i-clasificacion-alarma';
import {Title} from '@angular/platform-browser';
import {ClasificacionAlarma} from '../../clases/clasificacion-alarma';
import {ActivatedRoute, Router} from '@angular/router';
import {CargaClasificacionAlarmaService} from '../../servicios/carga-clasificacion-alarma.service';

@Component({
  selector: 'app-nueva-clasificacion-alarma',
  templateUrl: './nueva-clasificacion-alarma.component.html',
  styleUrls: ['./nueva-clasificacion-alarma.component.scss']
})

export class NuevaClasificacionAlarmaComponent implements OnInit {
  public clasificacion_alarma: IClasificacionAlarma;

  constructor(private titleService: Title, private route: ActivatedRoute, private cargaClasificacionesAlarmas: CargaClasificacionAlarmaService, private router: Router) {
  }

  ngOnInit(): void {
    this.titleService.setTitle('Nueva clasificación alarma');
    this.clasificacion_alarma = new ClasificacionAlarma();
  }

  nuevaClasificacionAlarma(): void {
    this.cargaClasificacionesAlarmas.nuevaClasificacionAlarma(this.clasificacion_alarma).subscribe(
      e => {
        console.log('Clasificación alarma creada');
        console.log(this.clasificacion_alarma);
        this.router.navigate(['/clasificaciones_alarmas']);
      },
      error => {
        console.log(error);
      }
    );
  }
}
