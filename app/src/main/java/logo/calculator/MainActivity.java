package logo.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

    }
    protected void Back(View V){
        EditText Ex=(EditText) findViewById(R.id.show);
        c= Ex.getText().toString();
        if(c.length()==1){
            c = "0";
            Ex.setText("" + c);
        }
        else {
            c = c.substring(0, c.length() - 1);
            Ex.setText("" + c);
        }
    }
    protected void Clear(View V){
        EditText Ex=(EditText) findViewById(R.id.show);
        Ex.setText("0");
        opd1=0;
        opd2=0;
        c="0";
    }
    private static double cal(char op, double p1, double p2) {
        switch(op) {
            case '+': return p1 + p2;
            case '-': return p1 - p2;
            case '*': return p1 * p2;
            case '/': return p1 / p2;
            case '%': return p1 % p2;
            default:  throw new ArithmeticException(op + " not defined");
        }
    }
    double opd1=0.00;
    double opd2=0.00;
    char op='0';
    char op2='0';
    int x=0;
    int y=0;
    int z=0;
    public static String c="0";
    protected void KeyInput(View V) {
        Button bt=(Button) V;
        c=c+ (String) bt.getText();
        EditText Ex=(EditText) findViewById(R.id.show);
        Ex.setText(""+c);
        if ("+-*/%=".indexOf((String) bt.getText()) != -1&&x==1) {
            op2=c.charAt(c.length() - 1);
            c = c.substring(0, c.length() - 1);
            opd2 = Double.parseDouble(String.valueOf(c));
            c="0";
            c=Double.toString(cal(op,opd1,opd2));
            Ex.setText(""+c);
            if(op2=='='){
                opd1=0;
                opd2=0;
                z=0;
            }
            else {
                opd1=Double.parseDouble(String.valueOf(c));
                opd2=0;
                z=1;
            }
            if(z==0)
                y=1;
            else c="0";
        }
        if ("+-*/%=".indexOf((String) bt.getText()) != -1&&x==0) {
            op=c.charAt(c.length() - 1);
            c = c.substring(0, c.length() - 1);
            opd1 = Double.parseDouble(String.valueOf(c));
            c="0";
            Ex.setText(""+c);
            if(op=='='){
                Ex.setText(""+c);
                opd1=0;
                opd2=0;
            }
            x=1;
            y=0;
        }
        if(y==1){
            x=0;
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        GridLayout keysGL = (GridLayout) findViewById(R.id.keys);
        int kbHeight =(int)(keysGL.getHeight()/ keysGL.getRowCount());
        int kbWidth =(int)(keysGL.getWidth()/ keysGL.getColumnCount());
        Button btn;
        for( int i=0; i< keysGL.getChildCount();i++)
        {
            btn = (Button) keysGL.getChildAt(i);
            btn.setHeight(kbHeight);
            btn.setWidth(kbWidth);
        }
    }


}




