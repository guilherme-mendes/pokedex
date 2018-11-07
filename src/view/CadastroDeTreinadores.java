package view;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/**
 * JDialog que gerencia o cadastro e gerenciamento dos treinadores.
 */
public class CadastroDeTreinadores extends javax.swing.JDialog {

    private final String CABECALHO_TREINADORES[] = {"ID","NOME","REGIÃO"};
    private final String CABECALHO_POKEMONS[] = {"ID","NOME","TIPO 1","TIPO 2"};
    
    private int ultimoIdTrinador;
    
    ArrayList<model.Treinador> treinadores = new ArrayList();
    ArrayList<model.PokemonDeTreinador> pokemons;
    ArrayList<model.PokemonDeTreinador> pokemonsDeTrinadores;
    
    ArrayList<model.Treinador> treinadoresFiltrados = new ArrayList();
    ArrayList<model.PokemonDeTreinador> pokemonsFiltrados = new ArrayList();
    
    ArrayList<model.PokemonDeTreinador> pokemonsInTabela = new ArrayList();
    
    private java.awt.Frame parent;
    
    public CadastroDeTreinadores(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        
        this.parent = parent;
        this.pokemons = model.InOut.importarDadosPokemonDeTreinador(view.JanelaPrincipal.ARQUIVO_POKEMONS_TREINADORES);
        
        atualizarDados();
        setLocationRelativeTo(null);
    }
    
    /**
     * Atualiza os dados e tabelas.
     */
    private void atualizarDados(){
        this.treinadores = model.InOut.importarDadosTreinadores();
        this.treinadoresFiltrados = this.treinadores;
        this.pokemonsDeTrinadores = model.InOut.importarDadosPokemonDeTreinador(view.JanelaPrincipal.ARQUIVO_POKEMONS_TREINADORES);
        
        if(this.treinadores != null){
            if(this.treinadores.size() > 0){
                this.ultimoIdTrinador = this.treinadores.get(this.treinadores.size() - 1).getId();
            }
        }
        
        
        atualizarTabelaTreinadores(this.treinadores);
    }
    
     /**
     * Preenche a tabela com os dados dos treinadores.
     * @param array 
     * Um array de treinadores
     */
    private void atualizarTabelaTreinadores(ArrayList<model.Treinador> array) {
        if (array != null) {
            String[][] retorno = new String[array.size()][3];

            for (int i = 0; i < array.size(); i++) {
                retorno[i][0] = "" + array.get(i).getId();
                retorno[i][1] = "" + array.get(i).getNome();
                retorno[i][2] = "" + array.get(i).getRegiao();

            }

            jTableTreinadores.setModel(new javax.swing.table.DefaultTableModel(retorno, this.CABECALHO_TREINADORES));

            TableColumn colunaTelefoneCelular = jTableTreinadores.getColumnModel().getColumn(0);
            colunaTelefoneCelular.setPreferredWidth(10);

        }
    }
    
     /**
     * Preenche a tabela com alguns dados dos pokemons.
     * @param array 
     * Um array de pokemons
     */
    private void atualizarTabelaPokemon(ArrayList<model.PokemonDeTreinador> array) {
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
     * Seleciona o treinado e envia-o para a janelas de vinculação de pokemons.
     */
    private void vincular(){
        int colunaTabela = jTableTreinadores.getSelectedRow();

        if (colunaTabela != -1) {
            if (new view.JDialogVinculacaoDePokemon(this.parent, this.treinadoresFiltrados.get(colunaTabela)).exibir()) {
                atualizarDados();
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECIONE UM TREINADOR!", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Seleciona o treinador para mostrar os pokemons de sua lista.
     */
    private void selecionarTrinador(){
        int colunaTabela = jTableTreinadores.getSelectedRow();

        if (colunaTabela != -1) {
            ArrayList<model.PokemonDeTreinador> pokemons = new ArrayList();
            
            for(int i = 0; i < this.pokemonsDeTrinadores.size(); i++){
                if(this.treinadoresFiltrados.get(colunaTabela).getId() == this.pokemonsDeTrinadores.get(i).getIdTreinador()){
                    pokemons.add(this.pokemonsDeTrinadores.get(i));
                }
            }
            this.pokemonsInTabela = pokemons;
            this.pokemonsFiltrados = this.pokemonsInTabela;
            atualizarTabelaPokemon(pokemons);
        }
    }
    
    /**
     * Seleciona o pokemon para abrir a janela de detalhes.
     */
    private void selecionarPokemon(){
        int colunaTabela = jTablePokemons.getSelectedRow();

        if (colunaTabela != -1) {
            new view.DetalhesPokemon(this.parent, this.pokemonsFiltrados.get(colunaTabela)).exibir();
        }          
    }
    
    /**
     * Pesquisa treinadores por nome.
     * @param conteudoPesquisa
     * Uma String que sera comparada com o nome.
     */
    private void pesquisarTreinador(String conteudoPesquisa) {
        ArrayList<model.Treinador> retorno = new ArrayList();

        for (int i = 0; i < this.treinadores.size(); i++) {

            try {
                if (String.valueOf(this.treinadores.get(i).getNome()).toLowerCase().substring(0, conteudoPesquisa.length()).equals(conteudoPesquisa.toLowerCase())) {
                    retorno.add(this.treinadores.get(i));
                    continue;
                }
            } catch (Exception e) {e.printStackTrace();
            }
        }

        this.treinadoresFiltrados = retorno;
        atualizarTabelaTreinadores(retorno);

    }
    
    /**
     * Pesquisa pokemons por nome.
     * @param conteudoPesquisa
     * Uma String que sera comparada com o nome.
     */
    private void pesquisarPokemons(String conteudoPesquisa) {
        ArrayList<model.PokemonDeTreinador> retorno = new ArrayList();

        for (int i = 0; i < this.pokemonsInTabela.size(); i++) {

            try {
                if (String.valueOf(this.pokemonsInTabela.get(i).getName()).toLowerCase().substring(0, conteudoPesquisa.length()).equals(conteudoPesquisa.toLowerCase())) {
                    retorno.add(this.pokemonsInTabela.get(i));
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

            ArrayList<model.PokemonDeTreinador> retorno = new ArrayList();

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
            this.pokemonsFiltrados = this.pokemonsInTabela;
            atualizarTabelaPokemon(this.pokemonsFiltrados);
        }
    }

    public void exibir(){
        setVisible(true);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jTextFieldPesquisarPokemon1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTreinadores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jTextFieldPesquisarTreinador = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePokemons = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        jSeparator2 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/poke.png"))); // NOI18N
        jButton4.setText("VINCULAR POKÉMON");
        jButton4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

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

        jTableTreinadores.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableTreinadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableTreinadores.setFocusable(false);
        jTableTreinadores.setRowHeight(30);
        jTableTreinadores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableTreinadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableTreinadoresMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableTreinadores);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TREINADORES");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/lupa.png"))); // NOI18N
        jButton2.setText("PESQUISAR");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("TREINADORES:");

        jLabel4.setText("POKEMONS:");

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
        jScrollPane2.setViewportView(jTablePokemons);

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPesquisarPokemon1)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldPesquisarTreinador, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldPesquisarTreinador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPesquisarPokemon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addGap(0, 147, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        vincular();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        pesquisarPokemons(jTextFieldPesquisarPokemon1.getText());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        filtrarPokemons();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTableTreinadoresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTreinadoresMousePressed
        selecionarTrinador();
    }//GEN-LAST:event_jTableTreinadoresMousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        pesquisarTreinador(jTextFieldPesquisarTreinador.getText());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTablePokemonsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePokemonsMousePressed
        if(evt.getClickCount() == 2){
            selecionarPokemon();
        }
    }//GEN-LAST:event_jTablePokemonsMousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        if(new view.JDialogCadastroDeTreinador(this.parent, this.ultimoIdTrinador).exibir()){
            atualizarDados();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTablePokemons;
    private javax.swing.JTable jTableTreinadores;
    private javax.swing.JTextField jTextFieldPesquisarPokemon1;
    private javax.swing.JTextField jTextFieldPesquisarTreinador;
    // End of variables declaration//GEN-END:variables
}
