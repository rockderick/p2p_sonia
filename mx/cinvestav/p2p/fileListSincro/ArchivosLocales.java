package mx.cinvestav.p2p.fileListSincro;

public class ArchivosLocales {

	/**
	 * Metodo para reguntar si un archivo este en alguno de los pares simples conectados a este super-par
	 * @param file nombre del archivo que se busca
	 * @return true cuando el archivo lo tiene uno de los pares simples del super-par, false de lo contrario
	 */
	synchronized public boolean isLocal(String file)
	{
		
		return false;
	}
	
	/**
	 * Metodo para obtener la ip del par simple que tiene el archivo file
	 * @param file archivo que se busca
	 * @return la ip de algun par simple que tiene el archivo, null si no se tiene el archivo
	 */
	synchronized public String getParSimpleIp(String file)
	{
		return null;
	}
}
