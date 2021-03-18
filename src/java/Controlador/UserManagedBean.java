package Controlador;

import Modelo.Arrendador;
import Modelo.ArrendadorDAO;
import Modelo.Arrendatario;
import Modelo.ArrendatarioDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

@Named(value = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {

    /**
     * Creates a new instance of UserManagedBean
     */
    private String code;

    private int tipo;
    private String cedula;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    Arrendatario arrendatario = new Arrendatario();
    ArrendatarioDAO arrendatarioDAO = new ArrendatarioDAO();

    Arrendador arrendador = new Arrendador();
    ArrendadorDAO arrendadorDAO = new ArrendadorDAO();

    public UserManagedBean() {
        code = "XULES-CODE";
        System.out.println("Validation code (Código de validación): " + code);
    }

    public String getValidation() {
        try {
            if (tipo != 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
                ExternalContext ec = context.getExternalContext();
                session.invalidate();
                if (tipo == 1) {
                    arrendatario.setCedula(cedula);
                    if (arrendatarioDAO.buscar(arrendatario)) {

                        return "<p>" + arrendatario.getNombres() + " es arrendatario</p>";
                    }

                } else if (tipo == 2) {
                    arrendador.setCedula(cedula);
                    arrendadorDAO.buscar(arrendador);
                    if (arrendador.getClave().equals(password)) {
                        ec.redirect(ec.getRequestContextPath()
                                + "/faces/departamentos.xhtml");
                        //return "<p>"+arrendador.getNombres()+"Es arrendador</p>";
                    } else {
                        return "<p>No es  arrendador</p>";
                    }

                }
            } else {
                return "<p>No válido</p>";
            }
        } catch (Exception e) {
        }
//        if ((validationCode != null) && (validationCode.compareTo(code) == 0)) {
//            // The validationCode is OK then we show the user data in validation.xhtml
//            // El código validationCode es correcto entonces mostramos los datos en validation.xhtm
//            FacesContext context = FacesContext.getCurrentInstance();
//            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
//            session.invalidate();
//            return "<p>User accepted:</p>"
//                    + "<ul>"
//                    + " <li>User: " + getUser() + " </li>"
//                    + " <li>Nick name: " + getNickName() + " </li>"
//                    + " <li>Email: " + getEmail() + " </li>"
//                    + "</ul>";
//        } else {
//            return "<p>Sorry, " + validationCode + " isn't valid.</p>"
//                    + "<p>Try again...</p>";
//        }
        return "";
    }
}
