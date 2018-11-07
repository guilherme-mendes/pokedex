package view;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/**
 * JDialog responsável por vincular pokemons á treinadores.
 */
public class JDialogVinculacaoDePokemon extends javax.swing.JDialog {

    private final String CABECALHO_POKEMONS[] = {"ID","NOME","TIPO"};
    
    private model.Treinador treinador;
    private ArrayList<model.Pokemon> pokemons;
    private ArrayList<model.Pokemon> listaDePokemons;
    
    private boolean retorno;
    
    public JDialogVinculacaoDePokemon(java.awt.Frame parent,model.Treinador treinador) {
        super(parent, true);
        initComponents();
        
        this.treinador = treinador;
        this.pokemons = view.JanelaPrincipal.pokemons;
        this.listaDePokemons = new ArrayList();
        
        listar(this.pokemons);
        
        jLabel1NomeTreinador.setText(treinador.getNome());
        setLocationRelativeTo(null);
    }
    
    /**
     * Preenche a tabela com alguns dados dos pokemons.
     * @param array 
     * Um array de pokemons
     */
    private void atualizarTabelaPokemon(ArrayList<model.Pokemon> array) {
        if (array != null) {
            String[][] retorno = new String[array.size()][4];

            for (int i = 0; i < array.size(); i++) {
                retorno[i][0] = "" + array.get(i).getId();
                retorno[i][1] = "" + array.get(i).getName();
                retorno[i][2] = "" + array.get(i).getType1();
                retorno[i][3] = "" + array.get(i).getType2();
            }

            jTablePokemons.setModel(new javax.swing.table.DefaultTableModel(retorno, this.CABECALHO_POKEMONS));

            TableColumn colunaTelefoneCelular = jTablePokemons.getColumnModel().getColumn(1);
            colunaTelefoneCelular.setPreferredWidth(500);
        }
    }
    
    /**
     * Preenche o JList com os nomes dos pokemons.
     * @param listaDePokemons 
     * Lista de pokemons para obter o nome dos mesmos.
     */
    private void listar(ArrayList<model.Pokemon> listaDePokemons){
        String [] lista = new String[listaDePokemons.size()];
        
        for(int i = 0; i < listaDePokemons.size(); i++){
            lista[i] = listaDePokemons.get(i).getName();
        }
        
        jComboBoxPokemons.setModel(new javax.swing.DefaultComboBoxModel<>(lista));
    }
    
    /**
     * Adiciona pokemons á tabela de adicionados.
     */
    private void add(){
        int index = jComboBoxPokemons.getSelectedIndex();
        this.listaDePokemons.add(this.pokemons.get(index));
        
        this.pokemons.remove(index);
        listar(this.pokemons);
        atualizarTabelaPokemon(this.listaDePokemons);
    }

    /**
     * Salva em CSV os pokemons vinculados ao treinadore recebido como parâmetro no construtor.
     * @return 
     */
    private boolean salvar() {
        for(int i = 0; i < this.listaDePokemons.size(); i++){
            model.InOut.escreverArquivo(this.listaDePokemons.get(i).getId() + "," + treinador.getId() + "," + this.listaDePokemons.get(i).getName() + "," + this.listaDePokemons.get(i).getType1() + "," + this.listaDePokemons.get(i).getType2() + "," + this.listaDePokemons.get(i).getTotal() + "," + this.listaDePokemons.get(i).getHp() + "," + this.listaDePokemons.get(i).getAttack() + "," + this.listaDePokemons.get(i).getDefense() + "," + this.listaDePokemons.get(i).getAtk()+ "," + this.listaDePokemons.get(i).getSpDef()+ "," + this.listaDePokemons.get(i).getSpeed()+ "," + this.listaDePokemons.get(i).getGeneration()+ "," + this.listaDePokemons.get(i).isLegendary() + "," + this.listaDePokemons.get(i).getHeight() + "," + this.listaDePokemons.get(i).getWeight(),JanelaPrincipal.ARQUIVO_POKEMONS_TREINADORES);
        }

        retorno = true;
        JOptionPane.showMessageDialog(this, "SUCESSO!", "", JOptionPane.INFORMATION_MESSAGE);
        dispose();
        return true;
    }

    public boolean exibir(){
        setVisible(true);
        
        return retorno;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel1NomeTreinador = new javax.swing.JLabel();
        jComboBoxPokemons = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePokemons = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CADASTRO DE POKÉMONS");

        jLabel2.setText("POKEMON:");

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/salvar.png"))); // NOI18N
        jButton2.setText("SALVAR");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/x_1.png"))); // NOI18N
        jButton1.setText("CANCELAR");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("TREINADOR:");

        jLabel1NomeTreinador.setText(".");
        jLabel1NomeTreinador.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBoxPokemons.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTablePokemons.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTablePokemons.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTablePokemons.setFocusable(false);
        jTablePokemons.setRowHeight(30);
        jTablePokemons.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTablePokemons);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/cursor.png"))); // NOI18N
        jButton3.setText("ADD");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxPokemons, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14)
                        .addComponent(jButton3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1NomeTreinador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1NomeTreinador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxPokemons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jButton3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        salvar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        add();
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxPokemons;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel1NomeTreinador;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablePokemons;
    // End of variables declaration//GEN-END:variables
}
