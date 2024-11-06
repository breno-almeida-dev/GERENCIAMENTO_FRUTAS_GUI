import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		/*
		 * Preparando o caminho para a instância da classe que irá executar o programa,
		 * primeiramente é executado o método "invokeLater", que é responsável por agendar
		 * a execução do código dentro do "Runnable" para que seja executado dentro da EDT.
		 * 
		 * EDT é a thread responsável por manipular componentes da interface gráfica, e deve
		 * ser utilizada aqui, pois ela é dedicada ao processamento de eventos da GUI, como
		 * cliques, digitação e atualizações de tela.
		 * 
		 * No "Runnable" é instanciada a interface, que será executada na EDT.
		 * 
		 * Com isso é criado o objeto da classe "FrutaManagerGUI" que será exibida para o
		 * usuário.
		 */
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new FrutaManagerGUI();
			}
		});
	}

}
