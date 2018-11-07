package view;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.table.TableColumn;

/**
 * Janela principal da Pokedex.
 */
public class JanelaPrincipal extends javax.swing.JFrame {
    
    private final String CABECALHO_POKEMONS[] = {"ID","NOME","TIPO 1","TIPO 2"};
    
    //Diretórios dos dados.
    public static final String ARQUIVO_TREINADORES = "/data/treinadores.txt";
    public static final String ARQUIVO_POKEMONS = "/data/csv_files/POKEMONS_DATA_1.csv";
    public static final String ARQUIVO_POKEMONS2 = "/data/csv_files/POKEMONS_DATA_2.csv";
    public static final String ARQUIVO_POKEMONS_TREINADORES = "/data/pokemons_de_treinadores.txt";
    public static final String ARQUIVO_IMGS = "/data/images";
    
    public static ArrayList<model.Pokemon> pokemons = new ArrayList();
    private ArrayList<model.Pokemon> pokemonsFiltrados = new ArrayList();
   
     
    public JanelaPrincipal() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/icones/dex.png")).getImage());
        setLocationRelativeTo(null);
        
        new ThreadPokemon().start();
        
        // setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
    
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
     * Pesquisa pokemons por nome.
     * @param conteudoPesquisa
     * Uma String que sera comparada com o nome.
     */
    private void pesquisarPokemons(String conteudoPesquisa) {
        ArrayList<model.Pokemon> retorno = new ArrayList();

        for (int i = 0; i < this.pokemons.size(); i++) {

            try {
                if (String.valueOf(this.pokemons.get(i).getName()).toLowerCase().substring(0, conteudoPesquisa.length()).equals(conteudoPesquisa.toLowerCase())) {
                    retorno.add(this.pokemons.get(i));
                    continue;
                }
            } catch (Exception e) {
            }
        }

        this.pokemonsFiltrados = retorno;
        atualizarTabelaPokemon(retorno);
   }
    
    /**
     * Filtra os pokemons pelo type1 e type2;
     */
    private void filtrarPokemons() {
        if (jComboBoxTipo.getSelectedIndex() != 0) {

            ArrayList<model.Pokemon> retorno = new ArrayList();

            for (int i = 0; i < this.pokemons.size(); i++) {
                   if(this.pokemons.get(i).getType1().equals((String) jComboBoxTipo.getSelectedItem())){
                       retorno.add(this.pokemons.get(i));
                       continue;
                   }
                   
                   if(this.pokemons.get(i).getType2().equals((String) jComboBoxTipo.getSelectedItem())){
                       retorno.add(this.pokemons.get(i));
                       continue;
                   }
            }
            
           

            this.pokemonsFiltrados = retorno;
            atualizarTabelaPokemon(retorno);
        }else{
            this.pokemonsFiltrados = this.pokemons;
            atualizarTabelaPokemon(this.pokemonsFiltrados);
        }
    }
    
    /**
     * Seleciona o pokemon para abrir a janela de detalhes.
     */
    private void selecionarPokemon(){
        int colunaTabela = jTablePokemons.getSelectedRow();

        if (colunaTabela != -1) {
            new view.DetalhesPokemon(this, this.pokemonsFiltrados.get(colunaTabela)).exibir();
        }          
    }
    
    /**
     * Thread que faz a leitura dos dados dos pokemons gerais.
     */
    private class ThreadPokemon extends Thread{
        public void run(){
            pokemons = model.InOut.importarDadosPokemon(view.JanelaPrincipal.ARQUIVO_POKEMONS);
            pokemonsFiltrados = pokemons;
            atualizarTabelaPokemon(pokemons);
        }
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePokemons = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        jTextFieldPesquisarPokemon1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POKEDEX");
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("POKEDEX");

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
        jTablePokemons.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTablePokemonsMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePokemons);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/lupa.png"))); // NOI18N
        jButton5.setText("PESQUISAR");
        jButton5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBoxTipo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS", "Grass", "Poison", "Fire", "Water", "Bug", "Normal", "Electric", "Ground", "Fighting", "Dark", "Rock", "Psychic", "Dragon", "Fairy", "Ice", "Ghost", "Steel" }));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/filtro.png"))); // NOI18N
        jButton6.setText("FILTRAR");
        jButton6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/add.png"))); // NOI18N
        jButton3.setText("ADICIONAR TREINADOR");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxTipo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldPesquisarPokemon1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPesquisarPokemon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTablePokemonsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePokemonsMousePressed
        if (evt.getClickCount() == 2) {
            selecionarPokemon();
        }
    }//GEN-LAST:event_jTablePokemonsMousePressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        pesquisarPokemons(jTextFieldPesquisarPokemon1.getText());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        filtrarPokemons();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new view.CadastroDeTreinadores(this).exibir();    
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTablePokemons;
    private javax.swing.JTextField jTextFieldPesquisarPokemon1;
    // End of variables declaration//GEN-END:variables
}
