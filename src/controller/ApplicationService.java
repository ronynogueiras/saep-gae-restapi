package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import util.SaepApplicationServiceLayer;

public class ApplicationService implements SaepApplicationServiceLayer {

	@Override
	public void createPoints() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getSettings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getDocumentPDF() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getDocumentHTML() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getReportPDF() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getReportHTML() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int status() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		
		return rand.nextInt() % 2 == 0 ? 200 : 501 ;
	}

	@Override
	public String getIdsResolutions() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		
		int size = rand.nextInt(10);
		StringBuilder builder = new StringBuilder();
		for(short i=0;i<size;i++){
			builder.append(rand.nextInt());
			if(i != size-1){
				builder.append(";");
			}
		}
		return builder.toString();
	}

	@Override
	public InputStream getResolution(String uuid) throws IOException {
		InputStream pdfStream = new ByteArrayInputStream("resolucao.pdf".getBytes());
		return pdfStream;
	}

	@Override
	public boolean deleteResolution(String uuid) {
		return new Random().nextInt() % 2 == 0;
	}

	@Override
	public boolean createResolution(String uuid, String resolution) {
		return (uuid != null && resolution != null);
	}

}
