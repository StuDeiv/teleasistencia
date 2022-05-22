package com.example.teleappsistencia.ui.fragments.relacion_terminal_recurso_comunitario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.teleappsistencia.modelos.RecursoComunitario;
import com.example.teleappsistencia.modelos.RelacionTerminalRecursoComunitario;
import com.example.teleappsistencia.servicios.APIService;
import com.example.teleappsistencia.servicios.ClienteRetrofit;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.utilidades.Utilidad;
import com.example.teleappsistencia.modelos.Paciente;
import com.example.teleappsistencia.modelos.Persona;
import com.example.teleappsistencia.modelos.Terminal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertarRelacionTerminalRecursoComunitarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertarRelacionTerminalRecursoComunitarioFragment extends Fragment implements View.OnClickListener {

    private RelacionTerminalRecursoComunitario relacionTerminalRecursoComunitario;

    private Spinner spinnerTerminalInsertarRelacionTerminalRecursoComunitario;
    private Spinner spinnerRecursoComunitarioInsertarRelacionTerminalRecursoComunitario;
    private Button btnInsertarRelacionTerminalRecursoComunitario;
    private Button btnVolverRelacionTerminalRecursoComunitarioInsertar;

    public InsertarRelacionTerminalRecursoComunitarioFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static InsertarRelacionTerminalRecursoComunitarioFragment newInstance() {
        InsertarRelacionTerminalRecursoComunitarioFragment fragment = new InsertarRelacionTerminalRecursoComunitarioFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_insertar_relacion_terminal_recurso_comunitario, container, false);

        obtenerComponentes(root);
        inicializarSpinnerTerminal();
        inicializarSpinnerRecursoComunitario();
        // Inflate the layout for this fragment
        return root;
    }

    private void obtenerComponentes(View root) {
        spinnerTerminalInsertarRelacionTerminalRecursoComunitario = root.findViewById(R.id.spinnerTerminalInsertarRelacionTerminalRecursoComunitario);
        spinnerRecursoComunitarioInsertarRelacionTerminalRecursoComunitario = root.findViewById(R.id.spinnerRecursoComunitarioInsertarRelacionTerminalRecursoComunitario);
        btnInsertarRelacionTerminalRecursoComunitario = root.findViewById(R.id.btnInsertarRelacionTerminalRecursoComunitario);
        btnVolverRelacionTerminalRecursoComunitarioInsertar = root.findViewById(R.id.btnVolverRelacionTerminalRecursoComunitarioInsertar);

        btnInsertarRelacionTerminalRecursoComunitario.setOnClickListener(this);
        btnVolverRelacionTerminalRecursoComunitarioInsertar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInsertarPaciente:
                accionBotonGuardar();
                break;
            case R.id.btnVolverPacienteInsertar:
                accionBotonVolver();
                break;
        }
    }

    private void accionBotonGuardar() {

    }

    private void accionBotonVolver() {

    }

    private void inicializarSpinnerTerminal() {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();
        Call<List<Terminal>> call = apiService.getListadoTerminal("Bearer " + Utilidad.getToken().getAccess());
        call.enqueue(new retrofit2.Callback<List<Terminal>>() {
            @Override
            public void onResponse(Call<List<Terminal>> call, Response<List<Terminal>> response) {
                if (response.isSuccessful()) {
                    List<Terminal> listadoTerminales = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, convertirListaTerminales(listadoTerminales));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerTerminalInsertarRelacionTerminalRecursoComunitario.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Terminal>> call, Throwable t) {
                Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private List<String> convertirListaTerminales(List<Terminal> listadoTerminales) {
        List<String> listadoTerminalesString = new ArrayList<>();
        for (Terminal terminal : listadoTerminales) {
            listadoTerminalesString.add("Nº de terminal: " + terminal.getNumeroTerminal());
        }
        return listadoTerminalesString;
    }

    private void inicializarSpinnerRecursoComunitario() {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();
        Call<List<RecursoComunitario>> call = apiService.getListadoRecursoComunitario("Bearer " + Utilidad.getToken().getAccess());
        call.enqueue(new retrofit2.Callback<List<RecursoComunitario>>() {
            @Override
            public void onResponse(Call<List<RecursoComunitario>> call, Response<List<RecursoComunitario>> response) {
                if (response.isSuccessful()) {
                    List<RecursoComunitario> listadoRecursoComunitario = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, convertirListaRecursoComunitario(listadoRecursoComunitario));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerRecursoComunitarioInsertarRelacionTerminalRecursoComunitario.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<RecursoComunitario>> call, Throwable t) {
                Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private List<String> convertirListaRecursoComunitario(List<RecursoComunitario> listadoRecursoComunitario) {
        List<String> listadoRecursoComunitarioString = new ArrayList<>();
        for (RecursoComunitario recursoComunitario : listadoRecursoComunitario) {
            listadoRecursoComunitarioString.add(recursoComunitario.getNombre());
        }
        return listadoRecursoComunitarioString;
    }

}