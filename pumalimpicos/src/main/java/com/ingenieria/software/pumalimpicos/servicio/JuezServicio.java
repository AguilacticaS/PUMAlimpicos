package com.ingenieria.software.pumalimpicos.servicio;

import java.util.List;

import javax.transaction.Transactional;

import com.ingenieria.software.pumalimpicos.repositorio.*;
import com.ingenieria.software.pumalimpicos.modelo.Juez;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JuezServicio {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    private final JuezRepositorio juezRepositorio;

    @Autowired
    public JuezServicio(JuezRepositorio juezRepositorio) {
        this.juezRepositorio = juezRepositorio;
    }

    public  List<Juez> getJueces() {
        return juezRepositorio.findAll();
    }

    /*public List<Juez> getJuecesByDisciplina(Disciplina disciplina) {
        return juezRepositorio.findJuezByDisciplina(disciplina);
    }*/

    public Juez getJuezById(Long juezId) {
        return juezRepositorio.findById(juezId)
        .orElseThrow(() -> new IllegalStateException("no existe juez con el Id suministrado."));
    }

    public Juez getJuezByNombreYApellidos(String nombre, String apellidoPaterno, String apellidoMaterno) {
        return juezRepositorio.findJuezByNombreYApellidos(nombre, apellidoPaterno, apellidoMaterno)
        .orElseThrow(() -> new IllegalStateException("no existe juez con el nombre suministrado." + nombre));
    }

    /**
     * Método agregaJuez, para agregar un juez a la base de datos
     * @param juez - El juez que se agregará al sistema.
     **/
    public void agregaJuez(Juez juez) {
		juez.setPassword(passwordEncoder.encode(juez.getPassword()));
        juezRepositorio.save(juez);
        /*Optional<Juez> juezOptional = juezRepositorio.findJuezByNombreYApellidos(juez.getNombre(),
                                        juez.getApellidoPaterno(), juez.getApellidoMaterno());
        if (juezOptional.isPresent()) {
            //throw new IllegalStateException("ya existe un juez con ese nombre");
            juezRepositorio.save(juezOptional.get());
        } else {
            juezRepositorio.save(juez);
        }*/
    }

    public void eliminaJuez(Long juezId) {
		boolean exists = juezRepositorio.existsById(juezId);
		if(!exists) {
			throw new IllegalStateException(
				"juez con id " + juezId + " no existe.");
		}
		juezRepositorio.deleteById(juezId);
    }

    @Transactional
    public void actualizaJuez(Long juezId,
                                    String nombre,
                                    String apellidoP,
                                    String apellidoM,
                                    String email,
                                    String username,
                                    String password,
                                    String disciplina){
        String advertencia = "Juez con Id: " + juezId + "no existe.";
        Juez juez = juezRepositorio.findById(juezId)
                .orElseThrow(() -> new IllegalStateException(advertencia));

        boolean repeated = false;
        if (!nombre.equals("")){
            repeated = nombre.equals(juez.getNombre());
            if (!repeated) juez.setNombre(nombre);
        } 

        if (!apellidoP.equals("")){
            repeated = apellidoP.equals(juez.getApellidoP());
            if (!repeated) juez.setApellidoP(apellidoP);
        } 

        if (!apellidoM.equals("")){
            repeated = apellidoM.equals(juez.getApellidoM());
            if (!repeated) juez.setApellidoM(apellidoM);
        } 

        if (!email.equals("")){
            repeated = email.equals(juez.getEmail());
            if (!repeated) juez.setEmail(email);
        } 

        if (!username.equals("")){
            repeated = username.equals(juez.getUsername());
            if (!repeated) juez.setUsername(username);
        } 

        if (!password.equals("")){
            repeated = passwordEncoder.encode(password).equals(juez.getPassword());
            if (!repeated) juez.setPassword(passwordEncoder.encode(password));
        } 

        if (!disciplina.equals("")){
            repeated = disciplina.equals(juez.getDisciplina());
            if (!repeated)juez.setDisciplina(disciplina);
        } 

    }
}
