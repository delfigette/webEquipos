import { Component, OnInit } from '@angular/core';
import { Jugador } from './jugador';
import { JugadorService } from './jugador.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

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


  public onAgregar(agregarForm: NgForm): void{
    document.getElementById('cerrar-agregar')?.click();
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

  public onEditar(jugador: Jugador): void{
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
