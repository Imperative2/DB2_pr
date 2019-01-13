package application;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimerTask;

import javax.swing.JLabel;

public class MyClock extends TimerTask
{
	JLabel labelTime;

	public MyClock(JLabel labelTime)
	{
		super();
		this.labelTime = labelTime;

	}

	@Override
	public void run()
	{
		while (true)
		{
			GregorianCalendar timeNow = new GregorianCalendar();
			String timeString = "";
			if (timeNow.get(timeNow.DAY_OF_MONTH) > 9)
				timeString += timeNow.get(timeNow.DAY_OF_MONTH) + "-";
			else
				timeString += "0" + timeNow.get(timeNow.DAY_OF_MONTH) + "-";
			
			if (timeNow.get(timeNow.MONTH) > 8)
				timeString += (timeNow.get(timeNow.MONTH) + 1) + "-";
			else
				timeString += "0" + (timeNow.get(timeNow.MONTH) + 1) + "-";
			
			timeString += timeNow.get(timeNow.YEAR) + " ";
			
			if (timeNow.get(timeNow.HOUR_OF_DAY) > 9)
				timeString += timeNow.get(timeNow.HOUR_OF_DAY) + ":";
			else
				timeString += "0" + timeNow.get(timeNow.HOUR_OF_DAY) + ":";
			
			if (timeNow.get(timeNow.HOUR_OF_DAY) > 9)
				timeString += timeNow.get(timeNow.MINUTE) + ":";
			else
				timeString += "0" + timeNow.get(timeNow.MINUTE) + ":";
			
			if (timeNow.get(timeNow.SECOND) > 9)
				timeString += timeNow.get(timeNow.SECOND);
			else
				timeString += "0" + timeNow.get(timeNow.SECOND);

			labelTime.setText(timeString);
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
