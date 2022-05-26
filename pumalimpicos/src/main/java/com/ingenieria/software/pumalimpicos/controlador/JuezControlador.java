package com.ingenieria.software.pumalimpicos.controlador;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.ingenieria.software.pumalimpicos.modelo.Disciplina;
import com.ingenieria.software.pumalimpicos.modelo.Juez;
import com.ingenieria.software.pumalimpicos.servicio.JuezServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path = "/menujuez")
public class JuezControlador {

    private final JuezServicio juezServicio;

    @Autowired
    JuezControlador(JuezServicio juezServicio) {
        this.juezServicio = juezServicio;
    }

    @RequestMapping()
    public String verMenuJuez() {
        return "juez/menujuez";
    }

    @GetMapping("/buscar")
    public String verBuscarJuez() {
        //model.addAttribute("juez", new Juez());
       // List<Disciplina> enums = Arrays.asList(Disciplina.values());
      // model.addAttribute("disciplinas", enums);
        return "juez/buscar";
    }

    @GetMapping("/resultado_busqueda_juez")
    public String getJuez(HttpServletRequest request, Model model){ //, @ModelAttribute("disciplina") Disciplina disciplina) {
        //model.addAttribute("disciplinas", Disciplina.values());
        //Juez juez = juezServicio.getJuezById(Long.parseLong(request.getParameter("juezNombre")));
        Juez juez = juezServicio.getJuezByNombreYApellidos(request.getParameter("nombre"),
                                                           request.getParameter("apellidoPaterno"),
                                                           request.getParameter("apellidoMaterno"));
        //model.addAttribute("nombre", juez);
        //List<Juez> juecesByDisciplina =  juezServicio.getJuecesByDisciplina(disciplina);
        //model.addAttribute("juez", request.getParameter("juezNombre"));
        model.addAttribute("juez", juez);
        return "juez/resultado_busqueda_juez";
    }

    /*@PostMapping("/resultado_busqueda_juez")
    public String actualiza(HttpServletRequest request, Model model) {
        juezServicio.actualizaJuez(Long.parseLong(request.getParameter("id")), 
                                               request.getParameter("nombre"),
                                               request.getParameter("apellidoPaterno"),
                                               request.getParameter("apellidoMaterno"),
                                               request.getParameter("email"));
        Juez juez = juezServicio.getJuezByNombreYApellidos(request.getParameter("nombre"),
                                                           request.getParameter("apellidoPaterno"),
                                                           request.getParameter("apellidoMaterno"));
        model.addAttribute("juez", juez);
        System.out.println(juez.getNombre());
        return "juez/resultado_busqueda_juez";
    }*/

    @PostMapping("/resultado_busqueda_juez")
    public String actualizaJuez(@Valid @ModelAttribute("juez") Juez juez, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
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
        return "juez/actualizar";
    }
    /*@GetMapping("/actualizar")
    public String actualizaJuez(@RequestParam String nombre, 
                                @PathVariable("apellidoPaterno") String apellidoPaterno, 
                                @PathVariable("apellidoMaterno") String apellidoMaterno, 
                                Model model) {
        model.addAttribute("juez", juezServicio.getJuezByNombreYApellidos(nombre,
                                                    apellidoPaterno, apellidoMaterno));
        return "juez/actualizar";
    }*/

    @GetMapping("/agregar")
    public String agregarJuez(Model model) {
        Juez nuevoJuez = new Juez();
        model.addAttribute("juez", nuevoJuez);
        return "juez/agregar";
    }

    @PostMapping("/guardar")
    public String agregarJuez(@Valid @ModelAttribute("juez") Juez juez, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "juez/agregar";
        } 
        juezServicio.agregaJuez(juez);
        model.addAttribute("jueces", juezServicio.getJueces());
        return "juez/verlista";
    }

    /*@PostMapping("/agregar")
    public String registra(HttpServletRequest request, Model model) {
        Juez nuevoJuez = new Juez(
            request.getParameter("nombre"),
            request.getParameter("apellidoPaterno"),
            request.getParameter("apellidoMaterno"),
            request.getParameter("email"));
        juezServicio.agregaJuez(nuevoJuez);
        model.addAttribute("jueces", juezServicio.getJueces());
        return "juez/agregar";
    }*/

    @GetMapping("/verlista")
    public String verListaJueces(Model model) {
        model.addAttribute("jueces", juezServicio.getJueces());
        return "juez/verlista";
    }
}
