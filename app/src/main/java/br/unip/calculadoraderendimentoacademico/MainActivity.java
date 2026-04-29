    package br.unip.calculadoraderendimentoacademico;

    import android.graphics.Color;
    import android.os.Bundle;
    import android.text.Editable;
    import android.text.TextWatcher;
    import android.view.View;
    import android.widget.EditText;
    import android.widget.SeekBar;
    import android.widget.TextView;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    public class MainActivity extends AppCompatActivity {

        EditText nota1EditText;
        EditText nota2EditText;
        EditText notaExameEditText;

        SeekBar frequenciaSeekBar;
        EditText frequenciaEditText;
        EditText mediaEditText;
        EditText mediaFinalEditText;
        EditText situacaoEditText;
        TextView notaExame;
        TextView mediaFinalEditView;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);

            // Atribuimos os itens do XML para o Java
            nota1EditText = findViewById(R.id.nota1EditText);
            nota2EditText = findViewById(R.id.nota2EditText);
            notaExameEditText = findViewById(R.id.notaExameEditText);
            notaExameEditText.setVisibility(View.GONE);
            frequenciaSeekBar = findViewById(R.id.frequenciaSeekBar);
            frequenciaEditText = findViewById(R.id.frequenciaEditText);
            mediaEditText = findViewById(R.id.mediaEditText);
            situacaoEditText = findViewById(R.id.situacaoEditText);
            mediaFinalEditText = findViewById(R.id.mediaFinalEditText);
            mediaFinalEditText.setVisibility(View.GONE);
            notaExame = findViewById(R.id.notaExame);
            notaExame.setVisibility(View.GONE);
            mediaFinalEditView = findViewById(R.id.mediaFinalEditView);
            mediaFinalEditView.setVisibility(View.GONE);

            notaExameEditText.setEnabled(false);
            mediaFinalEditText.setEnabled(false);
            notaExame.setEnabled(false);
            mediaFinalEditView.setEnabled(false);

            //Definição da barra de frequencia;
            frequenciaSeekBar.setMax(100); // 0% a 100%
            frequenciaSeekBar.setProgress(0); //Começa em 0%
            frequenciaEditText.setText("0%");// define o texto exibido em 0 de inicio

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });



            //Listener do nota 1
            nota1EditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    calcularNota(); // Quando o texto mudar, ele puxa o metodo do calculo para atualizar
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
            //Listener do nota 1
            nota2EditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    calcularNota();// Quando o texto mudar, ele puxa o metodo do calculo para atualizar
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            //Listener da barra de frequencia
            frequenciaSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    frequenciaEditText.setText(progress + "%");
                    calcularNota(); // Quando alterar na barra, ele mostra o numero atual e puxa o metodo de calculo apra ver se a presença é valida
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });

            //Listener do nota Exame
            notaExameEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    calcularNota();// Quando o texto mudar, ele puxa o metodo do calculo para atualizar
                }

                @Override
                public void afterTextChanged(Editable s) {

                }

            });
        }

        private void calcularNota() {
            // Pega as notas e transforar em string para verificar se nao ta vazio
            String nota1Texto = nota1EditText.getText().toString();
            String nota2Texto = nota2EditText.getText().toString();
            String notaExameTexto = notaExameEditText.getText().toString();

            //  se as notas estiverem vazias, o código para aqui
            if (nota1Texto.isEmpty() || nota2Texto.isEmpty()) {
                situacaoEditText.setText("Aguardando notas");
                return;
            }

            // Pegando os valores e convertendo para double
            double n1 = Double.parseDouble(nota1Texto);
            double n2 = Double.parseDouble(nota2Texto);
            int freq = frequenciaSeekBar.getProgress();
            double mediaSemestral = (n1 + n2) / 2;

            if (freq < 75) {
                // Verifica se a frequencia é menor que 75%, se não reprovado por falta
                situacaoEditText.setText("Reprovado por falta!");
                situacaoEditText.setTextColor(Color.RED);
                mediaEditText.setText(String.valueOf(mediaSemestral));
                return; // Encerra aqui se reprovou por falta
            } else if (n1 < 0 || n1 > 10 || n2 < 0 || n2 > 10) {
                // Verifica se as notas sao de 0 a 10
                situacaoEditText.setText("ERRO: Notas devem ser entre 0 e 10!");
                situacaoEditText.setTextColor(Color.RED);
                return;
            } else {
                // faz o calculo da media
                mediaEditText.setText(String.valueOf(mediaSemestral));
            }

            if (mediaSemestral >= 7) {
                situacaoEditText.setText("Aprovado");
                situacaoEditText.setTextColor(Color.GREEN);
                notaExameEditText.setEnabled(false);
            } else {
                // Se a média for menor que 7, ele precisa de EXAME
                situacaoEditText.setText("Colocar Nota Exame");
                situacaoEditText.setTextColor(Color.YELLOW);
                notaExameEditText.setVisibility(View.VISIBLE);
                mediaFinalEditText.setVisibility(View.VISIBLE);
                mediaFinalEditView.setVisibility(View.VISIBLE);
                notaExame.setVisibility(View.VISIBLE);
                // Habilite o campo de exame aqui para o usuário digitar
                notaExameEditText.setEnabled(true);
                mediaFinalEditText.setEnabled(true);
                notaExame.setEnabled(true);
                mediaFinalEditView.setEnabled(true);

                // Lógica para calcular a média final se o usuário digitar o exame
                if (!notaExameTexto.isEmpty()) {
                    double exame = Double.parseDouble(notaExameTexto);
                    double mediaFinal = (mediaSemestral + exame) / 2;
                    mediaFinalEditText.setText(String.valueOf(mediaFinal));

                    if (mediaFinal >= 5) {
                        situacaoEditText.setText("Aprovado em Exame");
                        situacaoEditText.setTextColor(Color.GREEN);
                    } else {
                        situacaoEditText.setText("Reprovado por Nota");
                        situacaoEditText.setTextColor(Color.RED);
                    }
                }
            }
        }
    }