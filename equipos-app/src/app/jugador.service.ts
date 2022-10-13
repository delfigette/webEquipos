import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Jugador} from './jugador';
import { HttpClient, HttpHandler } from '@angular/common/http'
import { environment } from 'src/environments/environment';


@Injectable ({
    providedIn: 'root'
})
export class JugadorService{
    [x: string]: any;
    private apiServerUrl = environment.apiBaseUrl;
    
    constructor(private http: HttpClient) { }
    
    public getJugadores(): Observable<Jugador[]> {
        return this.http.get<Jugador[]>(`${this.apiServerUrl}/jugador/all`)
    }

    public getNumeros(): Observable<number[]>{
        return this.http.get<number[]>(`${this.apiServerUrl}/jugador/numeros`)
    }

    public addJugadores(jugador: Jugador): Observable<Jugador> {
        return this.http.post<Jugador>(`${this.apiServerUrl}/jugador/add`, jugador)
    }

    public updateJugadores(jugador: Jugador): Observable<Jugador> {
        return this.http.put<Jugador>(`${this.apiServerUrl}/jugador/update`, jugador)
    }

    public jugadoresConNombre(nombre: string): Observable<Jugador[]>{
        return this.http.get<Jugador[]>(`${this.apiServerUrl}/jugador/buscar/${nombre}`)
    }
    
    public jugadoresConNumero(numero: number): Observable<Jugador[]>{
        return this.http.get<Jugador[]>(`${this.apiServerUrl}/jugador/filtrar/${numero}`)
    }
}
