import { Component, OnInit } from '@angular/core';
import { Jugador } from './jugador';
import { JugadorService } from './jugador.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { ErrorNotification } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  jugadores: Jugador[] | undefined;
  jugadorEditado: Jugador | undefined;
  jugadorPorEliminar: Jugador | undefined;
  numeros: number[] | undefined;

  constructor(private jugadorService: JugadorService){}

  ngOnInit(){
    this.getJugadores();
    this.getNumeros();
  }

  public getJugadores(): void{
    this.jugadorService.getJugadores().subscribe(
      (response: Jugador[]) =>{
        this.jugadores = response;
      },
      (error: HttpErrorResponse) =>  {
        alert(error.message);
      }
    )
  }

  public getNumeros(): void{
    this.jugadorService.getNumeros().subscribe(
      (response: number[]) =>{
        this.numeros = response;
      },
      (error: HttpErrorResponse) =>  {
        alert(error.message);
      }
    )
  }

  public buscarJugadorPorNumero(numero:number):void{
    this.jugadorService.jugadoresConNumero(numero).subscribe(
      (response: Jugador[]) =>{
        this.jugadores = response;
      },
      (error: HttpErrorResponse) =>  {
        alert(error.message);
      }
      )
      if(numero === 999){
        this.getJugadores();
      }
          
  }

  public buscarJugadorPorNombre(nombre:string):void{

    if(nombre.length === 0 || !nombre){
      this.getJugadores();
    }
    else{
      this.jugadorService.jugadoresConNombre(nombre).subscribe(
        (response: Jugador[]) =>{
          this.jugadores = response;
        },
        (error: HttpErrorResponse) =>  {
          alert(error.message);
        }
     )
    }

   }
 

  public popUp(mode: String, jugador?: Jugador): void{
    const container = document.getElementById('containerPrincipal');
    const boton = document.createElement('button');
    boton.type = 'button';
    boton.style.display = 'none';
    boton.setAttribute('data-toggle', 'modal');
    if(mode === 'add'){
      boton.setAttribute('data-target', '#agregarJugador')
    }
    if(mode === 'edit'){
      this.jugadorEditado = jugador;
      boton.setAttribute('data-target', '#editarJugador')
    }
    if(mode === 'delete'){
      this.jugadorPorEliminar = jugador;
      boton.setAttribute('data-target', '#eliminarJugador')
    }
    if(container !== null ){
      container.appendChild(boton);
    }
    boton.click();
  }

  public verificarValidez(jugador: Jugador): boolean{
    return jugador.nombre !== undefined && jugador.nombre !== null && jugador.numero !== undefined && jugador.numero !== null &&  jugador.posicion !== undefined && jugador.posicion !== null && !isNaN(jugador.numero ) && jugador.numero >= 1 
  }

  public nombreYaExiste(jugador: Jugador): boolean{
    this.buscarJugadorPorNombre(jugador.nombre);
    return this.jugadores?.length === 0;
  }

  public onAgregar(agregarForm: NgForm): void{
    document.getElementById('cerrar-agregar')?.click();
    if(this.verificarValidez(agregarForm.value) && !this.nombreYaExiste(agregarForm.value.nombre) )
    {
      this.jugadorService.addJugadores(agregarForm.value).subscribe(
        (response: Jugador) => {
          console.log(response);
          this.getJugadores();
          agregarForm.reset();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
          agregarForm.reset();
        }
       );
    }
    else{
        agregarForm.reset();
        alert("Ya existe un jugador con ese nombre");
          
    }
   
  }

  
  public onEditar(jugador: Jugador): void{
   if(this.verificarValidez(jugador)){
     this.jugadorService.updateJugadores(jugador).subscribe(
       (response: Jugador) => {
         console.log(response);
         this.getJugadores();
       },
       (error: HttpErrorResponse) => {
         alert(error.message);
       }
     ); 
   }
  }

  public onEliminar(jugadorId: number| undefined): void{
    this.jugadorService.deleteJugadores(jugadorId).subscribe(
     (response: void) => {
       console.log(response);
       this.getJugadores(); 
     },
     (error: HttpErrorResponse) => {
       alert(error.message);
     }
    ); 
   }

}
