package application;
import application.panels.UserAdmissionPanel;

import java.awt.Color;
import java.util.GregorianCalendar;

import javax.swing.JLabel;

public class TimerAdmissionTime extends Thread
{
	private long waitTime;
	private GregorianCalendar userAdmissionTime;
	private JLabel label;
	private boolean flag = false;
	private UserAdmissionPanel userAdmissionPanel;
	
	public TimerAdmissionTime(GregorianCalendar userAdmissionTime, JLabel label, long waitTimeMilisec, UserAdmissionPanel userAdmissionPanel)
	{
		super();
		this.userAdmissionTime = userAdmissionTime;
		this.userAdmissionPanel = userAdmissionPanel;
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
				userAdmissionPanel.setEnabledList(false);
			}
			else
			{
				label.setForeground(Color.GREEN);
				flag = true;
				userAdmissionPanel.setEnabledList(true);
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
