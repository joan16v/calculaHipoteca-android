package com.calculahipoteca;

import java.text.DecimalFormat;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculaHipotecaActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculahipoteca);
        
        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	String prestamoapedir;
            	final EditText editPrestamo=(EditText) findViewById(R.id.editText1);
            	prestamoapedir=editPrestamo.getText().toString();
            	
            	String tipodeinteres;
            	final EditText editTipo=(EditText) findViewById(R.id.EditText01);
            	tipodeinteres=editTipo.getText().toString();      
            	
            	String anos;
            	final EditText editAnos=(EditText) findViewById(R.id.EditText02);
            	anos=editAnos.getText().toString();  
            	
                    final TextView txtCuota=(TextView) findViewById(R.id.textView2);
            	
            	if( prestamoapedir.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+") ) {
            		double prestamo = Double.valueOf(prestamoapedir);
            		if( tipodeinteres.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+") ) {
            			float tipointeres=Float.valueOf(tipodeinteres);
            			if( anos.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+") ) {
            				double numanos = Double.valueOf(anos);
            				//empieza el lio
            				float cuotatotal;
            				float interesmensual=tipointeres/12;
            				double numeses=numanos*12;
            				float test=(float) Math.pow( (1+(interesmensual/100)) , ((-1)*(numeses)) );
            				cuotatotal=(float) ((prestamo*interesmensual)/(100*(1-test)));
            				Float cuotaObject=new Float(cuotatotal);
            				DecimalFormat twoDForm = new DecimalFormat("#.##");
            				txtCuota.setText( "Cuota mensual: " + Float.valueOf(twoDForm.format(cuotaObject)).toString() + "�" );
            			} else {
                        	AlertDialog.Builder builder = new AlertDialog.Builder(CalculaHipotecaActivity.this);
                        	builder.setMessage("El valor de los anyos es erroneo! Tiene que ser un numero!")
                        	       .setCancelable(false)
                        	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        	           public void onClick(DialogInterface dialog, int id) {                	        	   
                        	           }
                        	       });
                        	builder.show();    
            			}
            		} else {
                    	AlertDialog.Builder builder = new AlertDialog.Builder(CalculaHipotecaActivity.this);
                    	builder.setMessage("El valor del tipo de interes es erroneo! Tiene que ser un porcentaje!")
                    	       .setCancelable(false)
                    	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    	           public void onClick(DialogInterface dialog, int id) {                	        	   
                    	           }
                    	       });
                    	builder.show();               			
            		}
            	} else {
                	AlertDialog.Builder builder = new AlertDialog.Builder(CalculaHipotecaActivity.this);
                	builder.setMessage("El valor del prestamo es erroneo! Tiene que ser una cantidad en euros!")
                	       .setCancelable(false)
                	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                	           public void onClick(DialogInterface dialog, int id) {                	        	   
                	           }
                	       });
                	builder.show();              		
            	}
                
            }
        });        
        
    }
}
