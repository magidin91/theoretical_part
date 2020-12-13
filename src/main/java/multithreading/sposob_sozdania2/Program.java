package multithreading.sposob_sozdania2;

class AffableThread extends Thread
{
	@Override
	public void run()	//Этот метод будет выполнен в побочном потоке
	{
		System.out.println("Привет из побочного потока!");
	}
}

public class Program
{
	static AffableThread mSecondThread;
	
	public static void main(String[] args)
	{
		mSecondThread = new AffableThread();	//Создание потока
		mSecondThread.start();					//Запуск потока
		
		System.out.println("Главный поток завершён...");
	}
}