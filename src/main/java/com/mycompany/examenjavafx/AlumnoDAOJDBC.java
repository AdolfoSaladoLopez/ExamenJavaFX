package com.mycompany.examenjavafx;

import com.mycompany.examenjavafx.models.Alumno;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlumnoDAOJDBC implements AlumnoDAO {

    @Override
    public ArrayList<Alumno> getAlumnos() {
        String query = "SELECT * FROM examen";
        ArrayList<Alumno> alumnos = new ArrayList();

        try (Statement st = JdbcUtils.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Alumno alumno = new Alumno();

                alumno.setId(rs.getInt("id"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellidos(rs.getString("apellidos"));
                alumno.setAd(rs.getInt("ad"));
                alumno.setSge(rs.getInt("sge"));
                alumno.setDi(rs.getInt("di"));
                alumno.setPmdm(rs.getInt("pmdm"));
                alumno.setPsp(rs.getInt("psp"));
                alumno.setEie(rs.getInt("eie"));
                alumno.setHlc(rs.getInt("hlc"));

                alumnos.add(alumno);

            }

        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alumnos;
    }

}
