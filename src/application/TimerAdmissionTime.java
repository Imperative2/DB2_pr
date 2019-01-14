package application;
import java.awt.Color;
import java.util.GregorianCalendar;

import javax.swing.JLabel;

public class TimerAdmissionTime extends Thread
{
	private long waitTime;
	private GregorianCalendar userAdmissionTime;
	private JLabel label;
	private boolean flag = false;
	
	public TimerAdmissionTime(GregorianCalendar userAdmissionTime, JLabel label, long waitTimeMilisec)
	{
		super();
		this.userAdmissionTime = userAdmissionTime;
		this.label = label;
		waitTime = waitTimeMilisec;
		this.start();
	}
	@Override
	public void run()
	{
		while(true && flag == false)
		{
			GregorianCalendar now = new GregorianCalendar();
			if(now.before(userAdmissionTime))
			{
				label.setForeground(Color.RED);
				flag = false;
			}
			else
			{
				label.setForeground(Color.GREEN);
				flag = true;
			}
			try
			{
				Thread.sleep(waitTime);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean isAfter()
	{
		return flag;
	}

}
