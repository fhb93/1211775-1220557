package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoadGame {

	public boolean loadOperation(LogicFacade lf) {
		FileReader input = null;
		File file = new File("C:\\Users\\save.txt");
		

		try {
			file.createNewFile();
			input = new FileReader(file);

			Scanner s = null;

			try {
				s = new Scanner(new BufferedReader(input));
				int i = 0;
				while(i < 16) {
					LogicFacade.getPinos().get(i).setCor(Integer.parseInt(s.nextLine()));
					LogicFacade.getPinos().get(i).setX(Integer.parseInt(s.nextLine()));
					LogicFacade.getPinos().get(i).setY(Integer.parseInt(s.nextLine()));
					LogicFacade.getPinos().get(i).setCasaInicial(Integer.parseInt(s.nextLine()));
					LogicFacade.getPinos().get(i).setCasaFinal(Integer.parseInt(s.nextLine()));
					LogicFacade.getPinos().get(i).setCasasAndadas(Integer.parseInt(s.nextLine()));
					if(Integer.parseInt(s.nextLine()) == 1) {
						LogicFacade.getPinos().get(i).isOnPath = true;
					}
					else 
						LogicFacade.getPinos().get(i).isOnPath = false;
					i++;
				}
				i = 0;
				while(i < 52) {
					LogicFacade.getCasas().get(i).setQtdPin(Integer.parseInt(s.nextLine()));
					i++;
				}
				i = 0;
				while(i < 6) {
					LogicFacade.getAmarelas().get(i).setQtdPin(Integer.parseInt(s.nextLine()));
					LogicFacade.getAzuis().get(i).setQtdPin(Integer.parseInt(s.nextLine()));
					LogicFacade.getVermelhas().get(i).setQtdPin(Integer.parseInt(s.nextLine()));
					LogicFacade.getVerdes().get(i).setQtdPin(Integer.parseInt(s.nextLine()));
					i++;
				}
				i = 0;
				while(i < 4) {
					LogicFacade.getColocacoes().get(i).setCor(Integer.parseInt(s.nextLine()));
					LogicFacade.getColocacoes().get(i).setColocacao(Integer.parseInt(s.nextLine()));
					i++;
				}
			} finally {
				if (s != null) {
					s.close();
				}
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;

		}
		return true;

	}
}