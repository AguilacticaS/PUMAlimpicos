package com.ingenieria.software.pumalimpicos.controlador;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;

import com.ingenieria.software.pumalimpicos.modelo.Juez;
import com.ingenieria.software.pumalimpicos.servicio.CompetidorServicio;
import com.ingenieria.software.pumalimpicos.servicio.DisciplinaServicio;
import com.ingenieria.software.pumalimpicos.servicio.JuezServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Clase JuezControlador, que controla la
 * comunicación entre la vista y el modelo.
 * @author Arturo Yitzack Reynoso Sánchez
 * @version 1.0
 **/
@Controller
@RequestMapping(path = "/menujuez")
public class JuezControlador {

    private final JuezServicio juezServicio;
    private final DisciplinaServicio disciplinaServicio;
    private final CompetidorServicio competidorServicio;

    /**
     * Constructor de JuezControlador.
     * @param juezServicio La clase servicio de la clase Juez.
     * @param disciplinaServicio La clase servicio de la clase Disciplina.
     **/
    @Autowired
    JuezControlador(JuezServicio juezServicio,
    DisciplinaServicio disciplinaServicio,
    CompetidorServicio competidorServicio) {
        this.juezServicio = juezServicio;
        this.disciplinaServicio = disciplinaServicio;
        this.competidorServicio = competidorServicio;
    }

    @GetMapping("/competidores")
    public String verCompetidores(Model model, Authentication auth) {
		String username = auth.getName();
		Juez juez = juezServicio.findByUsername(username);
        String disciplina = juez.getDisciplina();
        model.addAttribute("competidores", competidorServicio.getCompetidoresByDisciplina(disciplina));
        return "juez/competidores";
    }

    @RequestMapping()
    public String verMenuJuez() {
        return "juez/menujuez";
    }

    @GetMapping("/buscar")
    public String verBuscarJuez() {
        return "juez/buscar";
    }

   
    @GetMapping("/calificar/{id}")
    public String calificarCompetidor(@PathVariable("id") Long id, Model model) {
        model.addAttribute("competidor", competidorServicio.getCompetidor(id));
        return "juez/calificar";
    }

    @PostMapping("/calificar")
    public String califica(HttpServletRequest request, Model model, Authentication auth) {
        competidorServicio.calificarCompetidor(Long.parseLong(request.getParameter("id")),
                                               Long.parseLong(request.getParameter("calificacion")),
                                               request.getParameter("comentarios"));
		String username = auth.getName();
		Juez juez = juezServicio.findByUsername(username);
        String disciplina = juez.getDisciplina();
        model.addAttribute("competidores", competidorServicio.getCompetidoresByDisciplina(disciplina));
        return "juez/competidores";
    }


    @GetMapping("/resultado_busqueda_juez")
    public String getJuez(HttpServletRequest request, Model model) {
        List<Juez> jueces;
        String nombre, apellidoP, apellidoM;
        nombre = request.getParameter("nombre");
        apellidoP = request.getParameter("apellidoP");
        apellidoM = request.getParameter("apellidoM");
        if (!nombre.equals("") && !apellidoP.equals("") && !apellidoM.equals("")) {
            jueces = juezServicio.getJuezByNombreYApellidos(nombre,
                    apellidoP,
                    apellidoM);
        } else if (!nombre.equals("") && apellidoP.equals("") && apellidoM.equals("")) {
            jueces = juezServicio.getJuezByNombre(nombre);
        } else if (nombre.equals("") && !apellidoP.equals("") && apellidoM.equals("")) {
            jueces = juezServicio.getJuezByApellidoPaterno(apellidoP);
        } else if (nombre.equals("") && apellidoP.equals("") && !apellidoM.equals("")) {
            jueces = juezServicio.getJuezByApellidoMaterno(apellidoM);
        } else if (!nombre.equals("") && !apellidoP.equals("") && apellidoM.equals("")) {
            jueces = juezServicio.getJuezByNombreYApellidoPaterno(nombre, apellidoP);
        } else if (!nombre.equals("") && apellidoP.equals("") && !apellidoM.equals("")) {
            jueces = juezServicio.getJuezByNombreYApellidoMaterno(nombre, apellidoM);
        } else if (nombre.equals("") && !apellidoP.equals("") && !apellidoM.equals("")) {
            jueces = juezServicio.getJuezByApellidoPaternoYApellidoMaterno(apellidoP, apellidoM);
        } else {
            jueces = juezServicio.getJueces();
        }
        model.addAttribute("jueces", jueces);
        return "juez/resultado_busqueda_juez";
    }

    @PostMapping("/resultado_busqueda_juez")
    public String actualizaJuez(@Valid @ModelAttribute("juez") Juez juez, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "juez/actualizar";
        }
        model.addAttribute("jueces", juezServicio.getJueces());
        return "juez/verlista";
    }

    @PostMapping("/eliminar")
    public String eliminarJuez(HttpServletRequest request, Model model) {
        juezServicio.eliminaJuez(Long.parseLong(request.getParameter("id")));
        return "juez/menujuez";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarJuez(@PathVariable("id") Long id, Model model) {
        juezServicio.eliminaJuez(id);
        model.addAttribute("jueces", juezServicio.getJueces());
        return "juez/verlista";
    }

    @GetMapping("/actualizar")
    public String actualizaJuez(@RequestParam Long juezId, Model model) {
        Juez juez = juezServicio.getJuezById(juezId);
        model.addAttribute("juez", juez);
        model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
        return "juez/actualizar";
    }

    @PostMapping("/actualizar")
    public String actualiza(@Valid @ModelAttribute("juez") Juez juez, BindingResult bindingResult, Model model) {
        if (bindingResult.hasFieldErrors("email")) {
            model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
            return "juez/actualizar";
        }
        juezServicio.actualizaJuez(juez.getId(), juez.getNombre(), juez.getApellidoP(),
                juez.getApellidoM(), juez.getEmail(), juez.getUsername(), juez.getPassword(), juez.getDisciplina());
        model.addAttribute("jueces", juezServicio.getJueces());
        return "juez/verlista";
    }

    @GetMapping("/agregar")
    public String agregarJuez(Model model) {
        Juez nuevoJuez = new Juez();
        model.addAttribute("juez", nuevoJuez);
        model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
        return "juez/agregar";
    }

    @PostMapping("/guardar")
    public String agregarJuez(@Valid @ModelAttribute("juez") Juez juez, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
            return "juez/agregar";
        }
        juezServicio.agregaJuez(juez);
        model.addAttribute("jueces", juezServicio.getJueces());
        return "juez/verlista";
    }

    @GetMapping("/verlista")
    public String verListaJueces(Model model) {
        model.addAttribute("jueces", juezServicio.getJueces());
        return "juez/verlista";
    }
}
