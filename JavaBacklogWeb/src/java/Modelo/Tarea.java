/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Chris
 */
public class Tarea {
    
    int idTarea;
    String titulo;
    String detalle;
    String fecha_abre;
    String fecha_estima_cierre;
    String fecha_edicion;
    int idCategoria;
    int idPrioridad;
    int idEstado;
    int idUsuarioCrea;
    int idUsuarioResuelve;
    int idProyecto;

    public Tarea() {
    }

    public Tarea(int idTarea, String titulo, String detalle, String fecha_abre, String fecha_estima_cierre, String fecha_edicion, int idCategoria, int idPrioridad, int idEstado, int idUsuarioCrea, int idUsuarioResuelve, int idProyecto) {
        this.idTarea = idTarea;
        this.titulo = titulo;
        this.detalle = detalle;
        this.fecha_abre = fecha_abre;
        this.fecha_estima_cierre = fecha_estima_cierre;
        this.fecha_edicion = fecha_edicion;
        this.idCategoria = idCategoria;
        this.idPrioridad = idPrioridad;
        this.idEstado = idEstado;
        this.idUsuarioCrea = idUsuarioCrea;
        this.idUsuarioResuelve = idUsuarioResuelve;
        this.idProyecto = idProyecto;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getFecha_abre() {
        return fecha_abre;
    }

    public void setFecha_abre(String fecha_abre) {
        this.fecha_abre = fecha_abre;
    }

    public String getFecha_estima_cierre() {
        return fecha_estima_cierre;
    }

    public void setFecha_estima_cierre(String fecha_estima_cierre) {
        this.fecha_estima_cierre = fecha_estima_cierre;
    }

    public String getFecha_edicion() {
        return fecha_edicion;
    }

    public void setFecha_edicion(String fecha_edicion) {
        this.fecha_edicion = fecha_edicion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(int idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdUsuarioCrea() {
        return idUsuarioCrea;
    }

    public void setIdUsuarioCrea(int idUsuarioCrea) {
        this.idUsuarioCrea = idUsuarioCrea;
    }

    public int getIdUsuarioResuelve() {
        return idUsuarioResuelve;
    }

    public void setIdUsuarioResuelve(int idUsuarioResuelve) {
        this.idUsuarioResuelve = idUsuarioResuelve;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public String toString() {
        return "Tarea{" + "idTarea=" + idTarea + ", titulo=" + titulo + ", detalle=" + detalle + ", fecha_abre=" + fecha_abre + ", fecha_estima_cierre=" + fecha_estima_cierre + ", fecha_edicion=" + fecha_edicion + ", idCategoria=" + idCategoria + ", idPrioridad=" + idPrioridad + ", idEstado=" + idEstado + ", idUsuarioCrea=" + idUsuarioCrea + ", idUsuarioResuelve=" + idUsuarioResuelve + ", idProyecto=" + idProyecto + '}';
    }
    
}
