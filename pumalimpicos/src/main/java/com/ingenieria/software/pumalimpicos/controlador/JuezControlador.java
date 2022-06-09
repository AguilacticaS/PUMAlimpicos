package com.ingenieria.software.pumalimpicos.controlador;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.ingenieria.software.pumalimpicos.modelo.Juez;
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

@Controller
@RequestMapping(path = "/menujuez")
public class JuezControlador {

    private final JuezServicio juezServicio;
    private final DisciplinaServicio disciplinaServicio;

    @Autowired
    JuezControlador(JuezServicio juezServicio,
            DisciplinaServicio disciplinaServicio) {
        this.juezServicio = juezServicio;
        this.disciplinaServicio = disciplinaServicio;
    }

    @GetMapping("/competidores")
    public String verCompetidores() {
        // model.addAttribute("juez", new Juez());
        // List<Disciplina> enums = Arrays.asList(Disciplina.values());
        // model.addAttribute("disciplinas", enums);
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
