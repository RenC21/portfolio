package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import modelo.Objetos.Oferta;

public class Modelo {

    private String FILE_NAME = "ofertas.txt";

    public void serializarOfertas(ArrayList<Oferta> ofertas) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(ofertas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarOferta(Oferta nuevaOferta) {
        ArrayList<Oferta> ofertas = deserializarOfertas();
        if (ofertas == null) {
            ofertas = new ArrayList<>();
        }
        ofertas.add(nuevaOferta);
        serializarOfertas(ofertas);
    }
 
    public ArrayList<Oferta> deserializarOfertas() {
        ArrayList<Oferta> ofertas = new ArrayList<>(); 
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                ofertas = (ArrayList<Oferta>) obj;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ofertas;
    }

    public ArrayList<Oferta> obtenerOfertasRegistradas(){
    	ArrayList<Oferta> ofertasRegistradas = deserializarOfertas();
    	return ofertasRegistradas;	
    }
    
    public void calcularMejoresOfertas(){
    	ArrayList<Oferta> ofertasRegistradas = obtenerOfertasRegistradas();
    	AlgoritmoGoloso algoritmoGoloso = new AlgoritmoGoloso(ofertasRegistradas);
    	ArrayList<Oferta> resultado = algoritmoGoloso.calcularMejoresOfertas();
    	serializarOfertas(resultado);
    }
    
    public void vaciarArchivo() {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean verificarArchivoVacio() {
        File archivo = new File(FILE_NAME);

        try (FileInputStream fis = new FileInputStream(archivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            Object obj = ois.readObject();

            if (obj instanceof ArrayList<?>) {
                ArrayList<?> lista = (ArrayList<?>) obj;
                
                if (lista.isEmpty()) {
                    return true; 
                } else {
                    return false; 
                }
            } else {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true; 
    }
}
