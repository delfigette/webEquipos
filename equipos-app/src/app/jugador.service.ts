import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Jugador} from './jugador';
import { HttpClient, HttpHandler } from '@angular/common/http'


@Injectable ({
    providedIn: 'root'
})
export class JugadorService{
    private apiServerUrl = '';
    
    constructor(private http: HttpClient) { }
    
    public getJugadores(): Observable<Jugador[]> {
        return this.http.get<Jugador[]>('${this.apiServerUrl}/jugador/all')
    }

    public addJugadores(jugador: Jugador): Observable<Jugador> {
        return this.http.post<Jugador>(`${this.apiServerUrl}/jugador/add`, jugador)
    }

    public updateJugadores(jugador: Jugador): Observable<Jugador> {
        return this.http.put<Jugador>(`${this.apiServerUrl}/jugador/update`, jugador)
    }

    public deleteJugadores(jugadorId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/jugador/delete/${jugadorId}`)
    }
}
