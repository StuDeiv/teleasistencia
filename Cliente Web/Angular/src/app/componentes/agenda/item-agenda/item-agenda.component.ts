import {Component, Input, OnInit} from '@angular/core';
import {IAgenda} from "../../../interfaces/i-agenda";
import Swal from "sweetalert2";
import {environment} from "../../../../environments/environment";
import {ActivatedRoute, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {CargaAgendaService} from "../../../servicios/carga-agenda.service";

@Component({
  selector: 'app-item-agenda, [app-item-agenda]',
  templateUrl: './item-agenda.component.html',
  styleUrls: ['./item-agenda.component.scss']
})
export class ItemAgendaComponent implements OnInit {

  @Input() public agenda: IAgenda;
  @Input() public fechaToday: Date = null;

  constructor(private cargaAgendaService: CargaAgendaService, private titleService: Title, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  //Toast para el Alert indicando que la operación fue exitosa
  alertExito() :void {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      //El tiempo que permanece la alerta, se obtiene mediante una variable global en environment.ts
      timer: environment.timerToast,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
      }
    })

    Toast.fire({
      icon: 'success',
      title: environment.fraseEliminar,
    })
  }
  //Toast para el alert indicando que hubo algún error en la operación
  alertError() :void {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: environment.timerToast,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
      }
    })

    Toast.fire({
      icon: 'error',
      title: environment.fraseErrorEliminar
    })
  }

  modalConfirmacion(): void {
    Swal.fire({
      title: '¿Está seguro que desea eliminar este evento?',
      showCancelButton: true,
      confirmButtonText: 'Aceptar',
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        this.eliminarAgenda('agenda')
      }
    })
  }

  eliminarAgenda(ruta:string) : void{
    this.cargaAgendaService.borrarAgenda(this.agenda.id).subscribe(
      e=>{
        this.router.navigateByUrl(ruta+'/borrado/'+this.agenda.id, {skipLocationChange: true}).then(() => {
          this.router.navigate([ruta]);
        });
        //Si el elemento se ha borrado con exito, llama al método que muestra el alert de Exito
        this.alertExito()
      },
      error => {
        //Si ha habido algún error al eliminar el elemento, llama al método que muestra el alert de Error
        this.alertError()
      }
    )
  }
}
