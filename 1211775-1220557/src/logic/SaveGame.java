package logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGame {
	public boolean saveOperation(LogicFacade lf) {
		FileWriter output = null;
		File file = new File("\\Users\\save.txt");
		
		
		try {
			file.createNewFile();
			output = new FileWriter(file);
			int i = 0;
			while(i < 16) {
				output.write(LogicFacade.getPinos().get(i).getCor() + "\n"); 
				output.write(LogicFacade.getPinos().get(i).getX() + "\n");
				output.write(LogicFacade.getPinos().get(i).getY() + "\n");
				output.write(LogicFacade.getPinos().get(i).getCasaInicial() + "\n");
				output.write(LogicFacade.getPinos().get(i).getCasaFinal() + "\n");
				output.write(LogicFacade.getPinos().get(i).getCasasAndadas() + "\n");
				if(LogicFacade.getPinos().get(i).isOnPath) {
					output.write("1" +"\n");
				}
				else 
					output.write("0"+ "\n");
				i++;
			}
			i = 0;
			while(i < 52) {
				output.write(LogicFacade.getCasas().get(i).getQtdPin() + "\n");
				i++;
			}
			i = 0;
			while(i < 6) {
				output.write(LogicFacade.getAmarelas().get(i).getQtdPin() + "\n");
				output.write(LogicFacade.getAzuis().get(i).getQtdPin() + "\n");
				output.write(LogicFacade.getVermelhas().get(i).getQtdPin() + "\n");
				output.write(LogicFacade.getVerdes().get(i).getQtdPin() + "\n");
				i++;
			}
			i = 0;
			while(i < 4) {
				output.write(LogicFacade.getColocacoes().get(i).getCor()+ "\n");
				output.write(LogicFacade.getColocacoes().get(i).getColocacao()+ "\n");
				i++;
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
