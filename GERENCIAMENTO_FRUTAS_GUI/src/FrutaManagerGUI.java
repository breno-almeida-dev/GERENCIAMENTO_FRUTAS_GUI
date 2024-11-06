import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrutaManagerGUI {
	/*
	 * Inicialmente são declaradas as variáveis necessárias para desenvolvimento
	 * dos métodos do programa.
	 * Um "ArrayList" para armazenar as frutas.
	 * Um "DefaultListModel" para ser utilizado para representar os itens armazenados
	 * no "JList".
	 * E o próprio "JList" para apresentar a lista de frutas na interface.
	 */
	private ArrayList<String> frutas;
	private DefaultListModel<String> listModel;
	private JList<String> list;
	
	public FrutaManagerGUI() {
		/*
		 * Dentro desse construtor da classe é inicializada a lista de frutas como vazia, e
		 * o "listModel" para mantar os dados armazenados visíveis no "JList"
		 */
		frutas = new ArrayList<>();
		listModel = new DefaultListModel<>();
		
		/*
		 * Aqui é criada a instância da janela principal da interface, onde em seguida
		 * é adicionado o método de fechar a janela caso seja necessário, e então é 
		 * adicionada a proporção que a janela terá, e por fim através do "setLayout" é feita
		 * a divisão da janela principal em regiões para ser organizada posteriormente no
		 * código.
		 */
		JFrame frame = new JFrame("Gerenciador de Frutas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 300);
		frame.setLayout(new BorderLayout());
		
		
		/*
		 * Neste momento é realizada a instância de um cantainer para acomodar 
		 * os componentes que serão inseridos, e em seguida é adicionado o método
		 * "FlowLayout" para que este container seja organizado.
		 */
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		/*
		 * Aqui é adicionado um bloco de entrada para ser preenchido com os
		 * nomes das frutas que serão processadas no programa.
		 */
		JTextField frutaField = new JTextField(15);
		panel.add(frutaField);
		
		/*
		 * Em sequência são adicionados 3 botões, sendo esses, "Adicionar"; "Modificar";
		 * "Remover", que serão utilizados para executar suas respectivas funções, e também
		 * com os botões "Modificar" e "Remover" sendo iniciados como desativados pois não
		 * há nenhuma fruta selecionada no momento em que se abre o programa.
		 * 
		 * Logo após os botões é definido a região em que os botões estarão acomodados na 
		 * janela principal.
		 */
		JButton addButton = new JButton("Adicionar");
		panel.add(addButton);
		
		JButton modifyButton = new JButton("Modificar");
		modifyButton.setEnabled(false);
		panel.add(modifyButton);
		
		JButton removeButton = new JButton("Remover");
		removeButton.setEnabled(false);
		panel.add(removeButton);
		
		
		frame.add(panel, BorderLayout.NORTH);
		
		/*
		 * Aqui é realizada a instância do "JList" que será responsável por apresentar a lista
		 * de frutas armazenadas que é preenchida com o "listModel".
		 * 
		 * Em seguida é adicionado um "JScrollPane", que é utilizado caso a lista de frutas seja
		 * grande o suficiente que não ocnsiga apresentar todas as frutas armazenada na janela, com
		 * isso o "scrollPane" age para dar a opção de rolar para acessar as frutas não visíveis.
		 * 
		 * Com isso, para concluir o "scrollPane" é direcionado para a região que estará localizado.
		 */
		list = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(list);
		frame.add(scrollPane, BorderLayout.CENTER);
		
		/*
		 * Aqui é adicionado o botão que irá listar as frutas, sendo direcionado para a região de baixo
		 * da tela.
		 */
		JButton listButton = new JButton("Listar Frutas");
		frame.add(listButton, BorderLayout.SOUTH);
		
		
		/*
		 * Aqui é definido o método que insere a fruta na lista de frutas. Para isso é adicionado
		 * um "ActionListener" que recebe o sinal de que o botão de adicionar a fruta foi clicado
		 * e faz a verificação do campo de entrada de texto, caso haja algo escrito a fruta é adicionada
		 * na lista.
		 * Por fim o campo de entrada é novamente esvaziado e uma mensagem de sucesso é apresentada ao
		 * usuário.
		 */
		addButton.addActionListener(new ActionListener() { //OLHA AQUI
			@Override
			public void actionPerformed(ActionEvent e) {
				String novaFruta = frutaField.getText();
				if(!novaFruta.isEmpty()) {
				   frutas.add(novaFruta);
				   listModel.addElement(novaFruta);
				   frutaField.setText("");
				   JOptionPane.showMessageDialog(frame, novaFruta + " foi adicionada.");
				}
			}
		});
		
		/*
		 * Para executar o método de modificar o nome da fruta já adicionada na lista, quando o
		 * botão correspondente é clicado no programa, esse bloco de código é ativado, primeiramente
		 * a posição da fruta escolhida para ser modificada é adicionada a uma variável "int", em seguida
		 * uma condição "if" verifica se há uma linha selecionada para ser modificada, sendo real a
		 * condição a fruta é selecionada e um janela para a fruta ser reescrita é apresentada,
		 * 
		 * Com isso, outra condição "if" é utilizada para verificar se o campo da nova fruta adicionada
		 * não está nulo ou vazio, caso seja real a condição a nova fruta é finalmente adicionada e uma
		 * mensagem de sucesso é apresentada.
		 */
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();
				if (selectedIndex != -1) {
				   String frutaSelecionada = listModel.getElementAt(selectedIndex);
				   String novaFruta = JOptionPane.showInputDialog(frame, "Modificar " + frutaSelecionada + " para:", frutaSelecionada);
				   if (novaFruta != null && !novaFruta.isEmpty()) {
					   frutas.set(selectedIndex, novaFruta);
					   listModel.set(selectedIndex, novaFruta);
					   JOptionPane.showMessageDialog(frame, "Fruta " + frutaSelecionada + " foi modificada para " + novaFruta);
				   }
				} else {
					JOptionPane.showMessageDialog(frame, "Selecione uma fruta para modificar.");
				}
			}
		});
		
		/*
		 * Para concluir as funções dos botões principais do programa, quando selecionada
		 * uma fruta na lista, e em seguida o usuário clicar em remover, este bloco de código
		 * entra em ação. Inicialmente, semelhante ao bloco de modificação, a posição da fruta na
		 * lista é armazenada em uma variável do tipo "int" e uma condição "if" verifica se realmente
		 * uma fruta da lista foi selecionada, com a condição sendo real a fruta é removida, tanto do
		 * "ArrayList" de frutas, quanto do "DefaultListModel" e retorna a mensagem de sucesso.
		 * Porém, caso não tenha sido selecionada nenhuma fruta quando o botão de remover foi clicado
		 * uma mensagem de orientação é apresentada ao usuário.
		 */
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtém o índice do item selecionado na lista
				int selectedIndex = list.getSelectedIndex();
				if (selectedIndex != -1) {
					//Remove o item tanto da lista lógica (ArrayList) quanto da visual (DefaultListModel)
					String frutaRemovida = frutas.remove(selectedIndex);
					listModel.remove(selectedIndex);
					JOptionPane.showMessageDialog(frame, frutaRemovida + " foi removida.");
				} else {
					JOptionPane.showMessageDialog(frame, "Selecione uma fruta para remover.");
				}
			}
		});
		
		/*
		 * Já este bloco tem uma função focada nos botões de modificação e remoção. Anteriormente
		 * os botões foram inicializados como desativados, e isso foi realizado para que, quando 
		 * uma fruta da lista seja selecionada estes botões sejam disponibilizados para serem
		 * utilizados, e essa é a função deste bloco de código.
		 */
		list.addListSelectionListener(e -> {
			boolean selectionExists = !list.isSelectionEmpty();
			removeButton.setEnabled(selectionExists);
			modifyButton.setEnabled(selectionExists);
		});
		
		/*
		 * Por fim, a interface como um todo é habilitada para ser visível ao usuário.
		 */
		frame.setVisible(true);		
	}
}
