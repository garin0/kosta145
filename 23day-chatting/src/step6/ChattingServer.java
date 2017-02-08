package step6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*  ä�� ���� 
 *  �ټ��� Ŭ���̾�Ʈ�� ���������� ��� 
 *  Ŭ���̾�Ʈ�� ���� �޼����� �Է¹޾� 
 *  ������ ��� Ŭ���̾�Ʈ���� �޼����� ����Ѵ� 
 */
public class ChattingServer {
	/*
	 * ������ Ŭ���̾�Ʈ�� ����� ServerWorker ��ü���� �����Ѵ� �����ϸ� list�� add �ϰ� ���������Ǹ� list����
	 * remove �Ѵ� public boolean add(E e) public boolean remove(Object o)
	 * 
	 */
	private ArrayList<ServerWorker> list = new ArrayList<ServerWorker>();

	/*
	 * ChattingServer �� ������ ServerSocket ���� �� client ���ӽ� �Ź� accept�� �����Ͽ�
	 * ServerWorker < Thread ���� �� start �����ش� �� �� ������ ServerWorker�� list�� add �Ѵ�
	 */
	public void go() throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(5432);
			System.out.println("**ä�� ����**");
			while (true) {
				Socket socket = serverSocket.accept();
				ServerWorker serverWorker = new ServerWorker(socket);
				Thread t = new Thread(serverWorker);
				list.add(serverWorker);
				t.start();
			}
		} finally {
			if (serverSocket != null)
				serverSocket.close();
		}

	}

	// ������ ��� Ŭ���̾�Ʈ���� �޼����� �����ϴ� �޼���
	public void broadcast(String message) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).pw.print(message);
		}
	}

	class ServerWorker implements Runnable {
		private Socket socket;
		private String clientIp;
		private BufferedReader br;// Ŭ���̾�Ʈ�κ��� �Է¹޴� ��Ʈ��
		private PrintWriter pw;// Ŭ���̾�Ʈ�� ����ϴ� ��Ʈ��

		ServerWorker(Socket socket) {
			this.socket = socket;
			clientIp = "[" + socket.getInetAddress().toString() + "]";
			System.out.println(clientIp + "�� ����^0^");
			broadcast(clientIp + "�� ����^0^");
		}

		@Override
		public void run() {
			try {
				chatting();
			} catch (IOException e) {
				System.out.println(clientIp + "���� ����");
			} finally {
				list.remove(this); // ������ �������� ����Ʈ���� ����
				System.out.println(clientIp + " ������");
				broadcast(clientIp + " ������");
				try {
					closeAll();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}// run
			// ������ ��ü Ŭ���̾�Ʈ���� �Է¹��� �޼����� ������

		public void chatting() throws IOException {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(), true);
			while (true) {
				String message = br.readLine();
				if (message == null || message.equals("null") || message.equals("����"))
					break;
				broadcast(clientIp + "��: "+message);
			}
		}

		// ����� ��� �ڿ��� close �Ѵ�
		public void closeAll() throws IOException {
			if(br!=null)
				br.close();
			if(pw!=null)
				pw.close();
			if(socket!=null)
				socket.close();
		}
	}

	public static void main(String[] args) {
		try {
			new ChattingServer().go();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}