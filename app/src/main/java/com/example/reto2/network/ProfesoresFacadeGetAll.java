package com.example.reto2.network;

import com.example.reto2.beans.Cursos;
import com.example.reto2.beans.Materias;
import com.example.reto2.beans.Profesores;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProfesoresFacadeGetAll extends NetConfiguration implements Runnable {


    private ArrayList<Profesores> response;

    public ProfesoresFacadeGetAll() {
        super();
    }

    @Override
    public void run() {

        final String theUrl = theBaseUrl + "/profesores";
        try {
            // The URL
            URL url = new URL(theUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            // Sending...
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == 418) {
                // I am not a teapot...
                this.response = null;

            } else if (responseCode == HttpURLConnection.HTTP_OK) {
                // Response...
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));

                StringBuffer response = new StringBuffer();
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                }
                bufferedReader.close();

                // Processing the JSON...
                String theUnprocessedJSON = response.toString();

                JSONArray mainArray = new JSONArray(theUnprocessedJSON);

                this.response = new ArrayList<>();

                Profesores profesor;
                for (int i = 0; i < mainArray.length(); i++) {
                    JSONObject object = mainArray.getJSONObject(i);

                    profesor = new Profesores();
                    profesor.setIdProf((Integer) object.getInt("idProf"));
                    profesor.setIdUser((Integer) object.getInt("idUser"));
                    profesor.setDisponibilidad(object.getString("disponibilidad"));
                    profesor.setTitulacion(object.getString("titulacion"));


                    JSONArray arrayMaterias = new JSONArray((List) object.getJSONArray("materiaImpartida"));
                    List <Materias> listMaterias = new ArrayList <Materias>();
                    Materias materias;
                    for (int j = 0; j < arrayMaterias.length(); j++) {
                        JSONObject objectM = arrayMaterias.getJSONObject(j);

                        materias = new Materias();
                        materias.setIdMateria((Integer) object.getInt("idMateria"));
                        materias.setNombreMateria(object.getString("nombreMateria"));
                        materias.setNivelMateria(object.getString("nivelMateria"));
                        materias.setTipoDeClase(object.getString("tipoDeClase"));
                        materias.setNumeroHoras(object.getString("numeroHoras"));


                        listMaterias.add(materias);
                    }
                    profesor.setMateriaImpartida(listMaterias);
                    profesor.setNivelEnsenanza(object.getString("nivelEnsenanza"));

                    JSONArray arrayCursos = new JSONArray((List) object.getJSONArray("materiaImpartida"));
                    List <Cursos> listCursos = new ArrayList <Cursos>();
                    Cursos cursos;
                    for (int j = 0; j < arrayCursos.length(); j++) {
                        JSONObject objectC = arrayCursos.getJSONObject(j);

                        cursos = new Cursos();
                        cursos.setIdCurso((Integer) object.getInt("idCurso"));
                        cursos.setNombreCurso(object.getString("nombreCurso"));
                        cursos.setNivelCurso(object.getString("nivelCurso"));
                        cursos.setNombreProfe(object.getString("nombreProfe"));


                        listCursos.add(cursos);
                    }
                    profesor.setCursoImp(listCursos);

                    profesor.setNombreprofe(object.getString("nombreProfe"));





                    this.response.add(profesor);
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public ArrayList<Profesores> getResponse() {
        return response;
    }
}