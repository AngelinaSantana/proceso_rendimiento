/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Angelina
 */

public class prueba_rendimiento extends javax.swing.JFrame {
//    public String codigo_sup=" ";
//    public String name_sup=" ";
//    public String cod_mat=" ";
//    public String name_mat=" ";
    public String recomendacion;
//    private String cod;
    /**
     * Creates new form prueba_rendimiento
     */
    public prueba_rendimiento() {
        initComponents();
        this.setTitle("Prueba de rendimiento");
        
//        Fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        String fechaActual = dateFormat.format(new Date());
        dtFecha.setText(fechaActual);

        this.CargarColor();
        this.CargarGrosor();
        this.setLocationRelativeTo(null);
    }
    
    public void CargarColor() {
        String Consulta;
        conexion conex = new conexion();
        ResultSet res;

        Consulta = "select * from color ";
// 
        res = conex.listar(Consulta);
            
        Vector v = new Vector();
//        cbcolor .addItem("No Aplica");
        try{
            while(res.next()){
                cbcolor.addItem(res.getString(2));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void CargarGrosor() {
        String Consulta;
        conexion conex = new conexion();
        ResultSet res;

        Consulta = "select * from grosor ";
// 
        res = conex.listar(Consulta);
            
        Vector v = new Vector();
//        cbgrosor.addItem("No Aplica");
        try{
            while(res.next()){
                cbgrosor.addItem(res.getString(2));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    
    public void calcular_aprovechado(){
            int aprovechado;
            int desperdicio;
            int tallo;
            int HMala;
            int picadura;
            int libra;
            
                if (!txtLibrasProbar.getText().isEmpty() && !txtLibraTallo.getText().isEmpty() && !txtLibraPicadura.getText().isEmpty() && !txtHojaMala.getText().isEmpty()){
                tallo = Integer.parseInt(txtLibraTallo.getText());
                picadura= Integer.parseInt(txtLibraPicadura.getText());
                HMala = Integer.parseInt(txtHojaMala.getText());
                libra = Integer.parseInt(txtLibrasProbar.getText());
        
                desperdicio = tallo + picadura + HMala;
                aprovechado = libra - desperdicio;
            
                Aprovechado.setText(String.valueOf(aprovechado));
            }else{
                JOptionPane.showMessageDialog(this, "Los campos Libras a probar, Libras Tallo, Libras Picadura y Hoja mala no pueden estar vacios.");
                return;
            }
                
        }
    

    public void busqueda(String cod_sup, String name_sup, String cod_mat, String name_mat){
        
        txtcodigo_sup.setText(cod_sup);
        txtname_sup.setText(name_sup);
        txtcod_mat.setText(cod_mat);
        txtname_mat.setText(name_mat);
//        
//        JOptionPane.showMessageDialog(this, "Funcion ejecutada, valor ");
      
    }    
    public void recomendacion() throws SQLException{
        if(!this.txtpromedio_hoja.getText().isEmpty() 
            && !this.txtRigidezInicial.getText().isEmpty()
            && !this.txtRigidezFinal.getText().isEmpty()
            && !this.txtLibraTallo.getText().isEmpty()
            && !this.txtGradoEmpaque.getText().isEmpty()
            && !this.txtGradoHumedad.getText().isEmpty()
            && !this.txtTiempoEntrega.getText().isEmpty() 
            && this.cbcolor.getSelectedIndex()!=0
            && this.cbgrosor.getSelectedIndex()!=0
            && !txtLibrasProbar.getText().isEmpty() 
            && !txtLibraTallo.getText().isEmpty() 
            && !txtLibraPicadura.getText().isEmpty() 
            && !txtHojaMala.getText().isEmpty()
//            && (!this.cb_xl.getText().isEmpty() 
//                || !this.cb_x.getText().isEmpty()
//                || !this.cb_l.getText())
            ){
            
            recomendacion="";
            String promedio_hoja = this.txtpromedio_hoja.getText(); 
            String RigidezInicial = this.txtRigidezInicial.getText();
            int RigidezFinal = Integer.parseInt(this.txtRigidezFinal.getText());
            String LibraTallo = this.txtLibraTallo.getText();
            int GradoEmpaque = Integer.parseInt(this.txtGradoEmpaque.getText());
            String GradoHumedad = this.txtGradoHumedad.getText();
            String color = this.cbgrosor.getSelectedItem().toString();
            String grosor = this.cbcolor.getSelectedItem().toString();
            String TiempoEntrega = this.txtTiempoEntrega.getText(); 
//            

//        
            
            //Calidad
            if(this.cb_l.isSelected())            
                recomendacion= recomendacion + "\n" +"La calidad de este tabaco no es factible para su uso, debido bajo rendimeiento "+
                        "que esto puede causar, no se recomienda tratamiento";
            
            //tamaño promedio de la hoja
            String Consulta;            
            int materia_prima = Integer.parseInt(txtcod_mat.getText());
            float tpromedio = Float.parseFloat(txtpromedio_hoja.getText());
            float tfinal = 0;
            float tinicial = 0;
//            
//            
            conexion conex = new conexion();
            ResultSet res;
//            
            Consulta = "select c.tamano_inicial, c.tamano_final from materia_prima m " +                    
                    "inner join clasificacion c"+
                    " on c.idclasificacion = m.clasificacion_idclasificacion "+
                    " WHERE m.idmateria_prima = " +materia_prima;
             res = conex.listar(Consulta);
             
             while(res.next()){             
                tinicial= Float.parseFloat(res.getString(1));
                tfinal= Float.parseFloat(res.getString(2));
            }
//            JOptionPane.showMessageDialog(this,"es "+tpromedio+ " "+tinicial+" "+tfinal);
            if(tpromedio <= tinicial || tpromedio >= tfinal){
//    
                recomendacion = recomendacion + "\n" + "El promedio de la hoja esta fuera de rango establecido en el tipo de tabaco,verifique el producto elegido";
            } 
            
            
            
            //Color de la hoja
            String color_temp = "";
            String Consult;            
            String name_mat2 = "";
            ResultSet rescolor;
//            int color_hoja
            
            name_mat2 = txtname_mat.getText().toString();
//            color_hoja =+ 1;
            
            Consult = "select co.desc_color from clasificacion c  inner join color co on co.id_color = c.id_color WHERE c.desc_clasificacion = '" +name_mat2+"'";
             rescolor = conex.listar(Consult);
             
             while(rescolor.next()){             
                if (!rescolor.getString(1).equals(cbcolor.getSelectedItem())) {
//                    JOptionPane.showMessageDialog(this, "Color no coincide el color con el tipo de tabaco seleccionado");
                    recomendacion = recomendacion + "\n" + "Color  no coincide con el tabaco seleccionado";
                    color_temp = rescolor.getString(1);
                }else if(cbcolor.getSelectedItem().equals("No Aplica")){
//                    JOptionPane.showMessageDialog(this, "Grosor no aplica para esta prueba de rendimiento");   
                    recomendacion = recomendacion + "\n" + "Color no aplica para esta prueba de rendimiento";
                    color_temp = rescolor.getString(1);
                }
                    
//                else{
//                    JOptionPane.showMessageDialog(this, "Color  no coincide");
//                }       
                
            }
             
             //grosor de la hoja
            String grosor_temp = "";
            String Consult2;                                  
            String name_mat3;
            ResultSet resgrosor;
//            int color_hoja
            
            name_mat3 = txtname_mat.getText().toString();
//            color_hoja =+ 1;
            
            Consult2 = "select g.desc_grosor from clasificacion c  inner join grosor g "
                    +" on g.id_grosor = c.id_grosor WHERE c.desc_clasificacion = '" +name_mat3+"'";
             resgrosor = conex.listar(Consult2);
             
             
             while(resgrosor.next()){             
                if (!resgrosor.getString(1).equals(cbgrosor.getSelectedItem())) {
//                    JOptionPane.showMessageDialog(this, "Grosor coincide")
                    recomendacion = recomendacion + "\n" + "Grosor  no coincide";
                    
                    grosor_temp = resgrosor.getString(1);
                }else if(cbgrosor.getSelectedItem().equals("No Aplica")){
//                    JOptionPane.showMessageDialog(this, "Grosor no aplica para esta prueba de rendimiento");   
                    recomendacion = recomendacion + "\n" + "Grosor no aplica para esta prueba de rendimiento";
                    grosor_temp = resgrosor.getString(1);
                }

            }
             
             
             
            //recomendacion de tratamiento
            if(     (RigidezFinal>=50 && RigidezFinal<=60) && (GradoEmpaque>=19 && GradoEmpaque<=21) &&
                    (this.cb_l.isSelected()==false && (tpromedio >= tinicial && tpromedio <= tfinal)))
//                    (color_temp.equals(cbcolor.getSelectedItem()) || cbcolor.getSelectedItem().equals("No Aplica")) &&
//                    (grosor_temp.equals(cbgrosor.getSelectedItem()) || cbgrosor.getSelectedItem().equals("No Aplica")))
            {
                
                
//                    JOptionPane.showMessageDialog(this, "Hasta Aqui bien."); 
//                if(RigidezFinal>=50 && RigidezFinal<=60){
                    recomendacion = "Pueba de rendimiento excelente, no necesita de tratamiento";
//                    
//                    JOptionPane.showMessageDialog(this, "Tambien esta bien hasta aqui"); 
//                 }
//                if(GradoEmpaque>=19 && GradoEmpaque<=21){
//                    recomendacion = "Pueba de rendimiento excelente, no necesita de tratamiento";
//                    
//                    JOptionPane.showMessageDialog(this, "Tambien esta bien hasta aqui"); 
//                 
//                }
                
            }else if((RigidezFinal <50 || RigidezFinal >60)|| (GradoEmpaque <19 || GradoEmpaque >21) || !rescolor.getString(1).equals(cbcolor.getSelectedItem()) ||
                    !resgrosor.getString(1).equals(cbgrosor.getSelectedItem())){ 
            
                //Grado de empaque            
                if(GradoEmpaque >21){
                    recomendacion= recomendacion + "" +"Tabaco en estado de fementación, no esta acto para empaque. No se recomienda tratamiento";
//                  JOptionPane.showMessageDialog(this, "El Tabaco en estado de fementación, aun no esta listo para empacar");
                }else if(GradoEmpaque <19){
                    recomendacion= recomendacion + "\n" +"El Tabaco no esta en el grado de acuedo para el empaque, la recomendacion es 19-21%";
                }
            
                //Humedad final
                if(RigidezFinal >60){
                    recomendacion= recomendacion + "\n" +"Haz sobrepasado el nivel maximo de humedad, debe de bajar el nivel de humedad para obtener "
                        +"mejor rendimiento";
                }else if(RigidezFinal <50){
                    recomendacion= recomendacion+ "\n" +"Nivel de humedad muy baja, Aumente el nivel de humedad minimo 50%% para obtener "+
                        "mejor rendimiento.";
                }
            
                int tiempo= Integer.parseInt(txtTiempoEntrega.getText());
                int tiempo_inicial = 0;
                int tiempo_final = 0;
                ResultSet resduration;
                
                String consult3= "Select duracion_inicial, duracion_final, name_tratamiento, desc_tratamiento from tipo_tratamiento";
                
                resduration= conex.listar(consult3);
                
                while(resduration.next()){
                    tiempo_inicial= Integer.parseInt(resduration.getString(1));
                    tiempo_final= Integer.parseInt(resduration.getString(2));
                    
                    if(tiempo>=tiempo_inicial && tiempo<=tiempo_final){
                    recomendacion = recomendacion + "\n El tratamiento adecuado para que el tabaco este en optima condiciones  "+
                            "es:"+resduration.getString(3)+".\n"+resduration.getString(4)+".";
                }
                }
            }
            
        }else{
            JOptionPane.showMessageDialog(this, "No Pueden haber campos vacios");
            return;
        }
        txtrecomendacion.setText(recomendacion);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dtFecha = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cb_xl = new javax.swing.JCheckBox();
        cb_x = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtLibrasProbar = new javax.swing.JTextField();
        cb_l = new javax.swing.JCheckBox();
        txtRigidezFinal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtRigidezInicial = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtLibraPicadura = new javax.swing.JTextField();
        txtLibrasFinal = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtLibraTallo = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtHojaMala = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtGradoHumedad = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtGradoEmpaque = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        Aprovechado = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTiempoEntrega = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtpromedio_hoja = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtrecomendacion = new javax.swing.JTextArea();
        cbgrosor = new javax.swing.JComboBox<>();
        cbcolor = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        txtcod_mat = new javax.swing.JTextField();
        txtname_mat = new javax.swing.JTextField();
        txtcodigo_sup = new javax.swing.JTextField();
        txtname_sup = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnrecomendar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnBuscar_materia = new javax.swing.JButton();
        btnNuevo1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PRUEBA DE RENDIMIENTO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(166, 166, 166))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel1)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1088, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Código:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 248, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Materia Prima:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 290, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Suplidor:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 332, -1, -1));

        jLabel5.setText("jLabel4");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));

        dtFecha.setEditable(false);
        jPanel1.add(dtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(861, 250, 141, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Fecha:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 248, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Rigidez inicial:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Calidad:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, -1, 30));

        cb_xl.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(cb_xl);
        cb_xl.setText("XL");
        cb_xl.setNextFocusableComponent(txtGradoEmpaque);
        cb_xl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_xlActionPerformed(evt);
            }
        });
        jPanel3.add(cb_xl, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, -1));

        cb_x.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(cb_x);
        cb_x.setText("X");
        cb_x.setNextFocusableComponent(txtGradoEmpaque);
        jPanel3.add(cb_x, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Recomendación:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Libras a probar:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 17, -1, -1));

        txtLibrasProbar.setFocusTraversalPolicyProvider(true);
        txtLibrasProbar.setNextFocusableComponent(txtRigidezInicial);
        jPanel3.add(txtLibrasProbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 15, 53, -1));

        cb_l.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(cb_l);
        cb_l.setText("L");
        cb_l.setName(""); // NOI18N
        cb_l.setNextFocusableComponent(txtGradoEmpaque);
        jPanel3.add(cb_l, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, -1, -1));

        txtRigidezFinal.setNextFocusableComponent(txtLibrasFinal);
        jPanel3.add(txtRigidezFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 53, -1));

        jLabel11.setText("%");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 120, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Rigidez final:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        txtRigidezInicial.setNextFocusableComponent(txtRigidezFinal);
        jPanel3.add(txtRigidezInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 50, -1));

        jLabel15.setText("%");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Libras final:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 110, 20));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Libra de la picadura:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, -1, -1));

        txtLibraPicadura.setNextFocusableComponent(txtLibraTallo);
        jPanel3.add(txtLibraPicadura, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 30, 60, -1));

        txtLibrasFinal.setNextFocusableComponent(cb_xl);
        jPanel3.add(txtLibrasFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 60, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Libra del tallo:");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, -1, -1));

        txtLibraTallo.setNextFocusableComponent(txtHojaMala);
        jPanel3.add(txtLibraTallo, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 70, 60, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Libra de hoja mala:");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, -1, -1));

        txtHojaMala.setNextFocusableComponent(txtpromedio_hoja);
        txtHojaMala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHojaMalaKeyPressed(evt);
            }
        });
        jPanel3.add(txtHojaMala, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, 60, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Grado de humedad:");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        txtGradoHumedad.setNextFocusableComponent(txtTiempoEntrega);
        jPanel3.add(txtGradoHumedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 60, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Grado de empaque:");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

        txtGradoEmpaque.setNextFocusableComponent(txtGradoHumedad);
        jPanel3.add(txtGradoEmpaque, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 60, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Aprovechado:");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 160, -1, -1));

        Aprovechado.setEditable(false);
        Aprovechado.setEnabled(false);
        Aprovechado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AprovechadoActionPerformed(evt);
            }
        });
        jPanel3.add(Aprovechado, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 160, 60, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Tiempo Entrega / Dias:");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, -1, -1));

        txtTiempoEntrega.setNextFocusableComponent(txtLibraPicadura);
        jPanel3.add(txtTiempoEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 60, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Tamaño Promedio de la hoja:");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 210, 20));
        jPanel3.add(txtpromedio_hoja, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 60, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Color de la hoja:");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 130, 20));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Color de la hoja:");
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 130, 20));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Grosor de la hoja:");
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 140, 20));

        jLabel28.setText("%");
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));

        jLabel29.setText("%");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, -1, -1));

        txtrecomendacion.setEditable(false);
        txtrecomendacion.setColumns(20);
        txtrecomendacion.setRows(5);
        txtrecomendacion.setToolTipText("Recomendación");
        jScrollPane2.setViewportView(txtrecomendacion);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 316, 740, 110));

        cbgrosor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        jPanel3.add(cbgrosor, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 160, -1));

        cbcolor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        jPanel3.add(cbcolor, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 160, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 943, 450));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 80, -1));
        jPanel1.add(txtcod_mat, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 292, 66, -1));

        txtname_mat.setEnabled(false);
        jPanel1.add(txtname_mat, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 292, 329, -1));
        jPanel1.add(txtcodigo_sup, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 334, 74, -1));

        txtname_sup.setEditable(false);
        txtname_sup.setBackground(new java.awt.Color(255, 255, 255));
        txtname_sup.setColumns(20);
        txtname_sup.setEnabled(false);
        jPanel1.add(txtname_sup, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 334, 286, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Autorizado por:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 374, -1, -1));

        jTextField6.setNextFocusableComponent(txtLibrasProbar);
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 376, 276, -1));

        btnSalvar.setBackground(new java.awt.Color(51, 51, 51));
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 890, 114, 40));

        btnrecomendar.setBackground(new java.awt.Color(51, 51, 51));
        btnrecomendar.setForeground(new java.awt.Color(255, 255, 255));
        btnrecomendar.setText("Recomendar");
        btnrecomendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrecomendarActionPerformed(evt);
            }
        });
        jPanel1.add(btnrecomendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 890, 120, 40));

        btnBorrar.setBackground(new java.awt.Color(51, 51, 51));
        btnBorrar.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrar.setText("BORRAR");
        jPanel1.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 890, 110, 40));

        btnSalir.setBackground(new java.awt.Color(51, 51, 51));
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 890, 100, 40));

        btnBuscar_materia.setBackground(new java.awt.Color(51, 51, 51));
        btnBuscar_materia.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar_materia.setText("Buscar");
        btnBuscar_materia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar_materiaActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar_materia, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 290, -1, -1));

        btnNuevo1.setBackground(new java.awt.Color(51, 51, 51));
        btnNuevo1.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo1.setText("NUEVO");
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 890, 100, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 1020));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void cb_xlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_xlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_xlActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnBuscar_materiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar_materiaActionPerformed
       consulta_materia_prima Vp = new consulta_materia_prima();      // creamos una ventana consulta de materia prima
       Vp.setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_btnBuscar_materiaActionPerformed

    private void AprovechadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AprovechadoActionPerformed
        calcular_aprovechado();
    }//GEN-LAST:event_AprovechadoActionPerformed

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void txtHojaMalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHojaMalaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.calcular_aprovechado();
        }
    }//GEN-LAST:event_txtHojaMalaKeyPressed

    private void btnrecomendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrecomendarActionPerformed
     
        try {
            this.recomendacion();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
        
        
        
    }//GEN-LAST:event_btnrecomendarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(prueba_rendimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(prueba_rendimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(prueba_rendimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(prueba_rendimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new prueba_rendimiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Aprovechado;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBuscar_materia;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnrecomendar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cb_l;
    private javax.swing.JCheckBox cb_x;
    private javax.swing.JCheckBox cb_xl;
    private javax.swing.JComboBox<String> cbcolor;
    private javax.swing.JComboBox<String> cbgrosor;
    private javax.swing.JFormattedTextField dtFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField txtGradoEmpaque;
    private javax.swing.JTextField txtGradoHumedad;
    private javax.swing.JTextField txtHojaMala;
    private javax.swing.JTextField txtLibraPicadura;
    private javax.swing.JTextField txtLibraTallo;
    private javax.swing.JTextField txtLibrasFinal;
    private javax.swing.JTextField txtLibrasProbar;
    private javax.swing.JTextField txtRigidezFinal;
    private javax.swing.JTextField txtRigidezInicial;
    private javax.swing.JTextField txtTiempoEntrega;
    private javax.swing.JTextField txtcod_mat;
    public javax.swing.JTextField txtcodigo_sup;
    private javax.swing.JTextField txtname_mat;
    public javax.swing.JTextField txtname_sup;
    private javax.swing.JTextField txtpromedio_hoja;
    private javax.swing.JTextArea txtrecomendacion;
    // End of variables declaration//GEN-END:variables
}

