package br.com.aula.projeto04_caixatexto;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void calcular(View view) {
        // Instanciamento dos objetos da interface XML:
        TextInputEditText campoNome = findViewById(R.id.textInputEditNome);
        TextInputEditText campoPeso = findViewById(R.id.textInputEditPeso);
        TextInputEditText campoAltura = findViewById(R.id.textInputEditAltura);
        TextView resultado1 = findViewById(R.id.textResultado1);
        TextView resultado2 = findViewById(R.id.textResultado2);

        // Obtendo o texto desses objetos em String:
        String nome = campoNome.getText().toString();
        String peso = campoPeso.getText().toString();
        String altura = campoAltura.getText().toString();

        try {
            // Convertendo tipos de variáveis (String -> Numerico):
            Double numPeso = Double.parseDouble(peso);
            Double numAltura = Double.parseDouble(altura);

            // Calculo do IMC com os dados fornecidos:
            Double numImc = numPeso / (numAltura * numAltura);

            // Formatação para apresentação de resultado:
            DecimalFormat df = new DecimalFormat("##.##");
            String imc = df.format(numImc);

            // Determinação da classificação do IMC:
            String classificacao;
            if (numImc < 18.5) {
                classificacao = "Baixo peso";
            } else if (numImc >= 18.5 && numImc <= 24.9) {
                classificacao = "Peso normal";
            } else if (numImc >= 25 && numImc <= 29.9) {
                classificacao = "Sobrepeso";
            } else if (numImc >= 30 && numImc <= 34.9) {
                classificacao = "Obesidade grau 1";
            } else if (numImc >= 35 && numImc <= 39.9) {
                classificacao = "Obesidade grau 2";
            } else {
                classificacao = "Obesidade extrema";
            }

            // Apresentação do resultado nos campos "resultado1" e "resultado2":
            resultado1.setText("IMC = " + imc + "kg/m²");
            resultado2.setText("Classificação: " + classificacao);

        } catch (NumberFormatException e) {
            // Se ocorrer erro na conversão dos números (entrada inválida):
            resultado1.setText("ERRO");
            resultado2.setText("ENTRADA INVÁLIDA");
        }
    }

    public void limpar(View view) {
        // Instanciamento dos objetos da interface XML:
        TextInputEditText campoNome = findViewById(R.id.textInputEditNome);
        TextInputEditText campoPeso = findViewById(R.id.textInputEditPeso);
        TextInputEditText campoAltura = findViewById(R.id.textInputEditAltura);
        TextView resultado1 = findViewById(R.id.textResultado1);
        TextView resultado2 = findViewById(R.id.textResultado2);

        // Limpar os campos de texto:
        campoNome.setText("");
        campoAltura.setText("");
        campoPeso.setText("");
        resultado1.setText("");
        resultado2.setText("");
    }
}
