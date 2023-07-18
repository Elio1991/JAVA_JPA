/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Libreria;

import Libreria.entidades.Autor;
import Libreria.entidades.Editorial;
import Libreria.servicios.AutorServicio;
import Libreria.servicios.EditorialServicio;
import Libreria.servicios.LibroServicio;



/**
 *
 * @author Elio
 */
public class MenuEjecucion {
    
    private final LibroServicio libroServ = new LibroServicio();
    private final AutorServicio autorServ = new AutorServicio();
    private final EditorialServicio editorialServ = new EditorialServicio();

    

// Crea autores, Editoriales y libros.   
    public void CrearEntidades(){
            Autor autor1 = autorServ.crearAutor("Dostoyevski");
            Autor autor2 = autorServ.crearAutor("J.K. Rowling");
            Autor autor3 = autorServ.crearAutor("George R.R. Martin");
            Autor autor4 = autorServ.crearAutor("Patrick Rothfuss");
            Autor autor5 = autorServ.crearAutor("C.S. Lewis");
            Autor autor6 = autorServ.crearAutor("Brandon Sanderson");
            Autor autor7 = autorServ.crearAutor("Neil Gaiman");
            Autor autor8 = autorServ.crearAutor("Margaret Weis y Tracy Hickman");
            
            Editorial editorial1 = editorialServ.crearEditorial("Pengüin");
            Editorial editorial2 = editorialServ.crearEditorial("Salamandra");
            Editorial editorial3 = editorialServ.crearEditorial("Gigamesh");
            Editorial editorial4 = editorialServ.crearEditorial("Plaza & Janes");
            Editorial editorial5 = editorialServ.crearEditorial("Destino");
            Editorial editorial6 = editorialServ.crearEditorial("Ediciones B");
            Editorial editorial7 = editorialServ.crearEditorial("Roca Editorial");
            Editorial editorial8 = editorialServ.crearEditorial("Timun Mas");
             
            libroServ.crearLibro(9788478884957L, "Harry Potter y la Piedra Filosofal", 1997, 1000, 200, 800, autor2, editorial2);
            libroServ.crearLibro(9788496208690L, "Canción de Hielo y Fuego", 1996, 800, 150, 650, autor3, editorial3);
            libroServ.crearLibro(9788401352836L, "El nombre del viento", 2007, 600, 50, 550, autor4, editorial4);
            libroServ.crearLibro(9788408062783L, "Las Crónicas de Narnia", 1950, 700, 100, 600, autor5, editorial5);
            libroServ.crearLibro(9788466657500L, "Elantris", 2005, 400, 80, 320, autor6, editorial6);
            libroServ.crearLibro(9788496208691L, "Tormenta de Espadas", 2000, 900, 200, 700, autor3, editorial3);
            libroServ.crearLibro(9788499895566L, "American Gods", 2001, 500, 70, 430, autor7, editorial7);
            libroServ.crearLibro(9788448034643L, "El Ciclo de la Puerta de la Muerte", 1984, 400, 80, 320, autor8, editorial8);
            libroServ.crearLibro(9788498387087L, "Animales Fantásticos y Dónde Encontrarlos", 2001, 400, 80, 320, autor2, editorial2);
            libroServ.crearLibro(9788498354287L, "Crimen y castigo", 1866, 50, 30, 20, autor1, editorial1);    
        
    }
    
    
    
}
