import { Component, OnInit } from '@angular/core';
import { Jugador } from './jugador';
import { JugadorService } from './jugador.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  public jugadores: Jugador[] = [];

  constructor(private jugadorService: JugadorService){}

  ngOnInit(){
    this.getJugadores();
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

}
