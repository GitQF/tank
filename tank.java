
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
public class tank{
	public static void main(String[] args)
	{	
		JFrame myframe=new JFrame("tank");
		mypanel pa=new mypanel();
		myframe.add(pa);
		Thread t=new Thread(pa);
		t.start();
		myframe.setSize(500,365);
		myframe.setLocation(200,100);
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myframe.setVisible(true);
		myframe.addKeyListener(pa);
		JMenuBar jmb=new JMenuBar();
		JMenu jm1=new JMenu("file ");
		JMenu jm2=new JMenu("star  ");
		JMenu jm3=new JMenu("help  ");
		JMenuItem jmi1=new JMenuItem("new  game ");
		jm1.add(jmi1);
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		myframe.setJMenuBar(jmb);
		
	}
}
class mypanel extends JPanel implements KeyListener,Runnable
{	
	boolean star=true;
	int a,b,c=0;
	hero hero;
	final int num=5;
	Vector<diren> di;
	public mypanel()
	{
		hero=new hero(140,260);
		hero.setSpeed(4);
		di=new Vector<diren>();
		for(int i=0;i<num;i++)
		{
			diren d=new diren(50*(i+1),0,(int)(Math.random()*3+1));
			d.setdirect(2);
			Thread r=new Thread(d);
			di.add(d);
			dan s =new dan(d.x+10,d.y+30,d.direct);
			Thread t2=new Thread(s);
			d.ss.add(s);
			if(star==true)
			{
			t2.start();
			r.start();
			}
		}

	}
	public void paint(Graphics g)
		{
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(0,0, 400, 300);
		g.setColor(Color.RED);
		g.fillRect(180, 260, 40, 40);
		if(hero.live==true)
		{
		this.drawtank(hero.getX(),hero.getY(),g,this.hero.direct,0);
		}
		if(hero.live==false)
		{
		g.drawString("GAME OVER",160,150);
		}
		this.drawdan(g);
		for(int i=0;i<di.size();i++)
		{
			diren en=di.get(i);
			if(en!=null&&en.live==true)
			{
			this.drawtank(en.getX(),en.getY(),g,en.getdirect(),en.type);
			}
			for(int j=0;j<en.ss.size();j++)
			{
				dan didan=en.ss.get(j);
				if(didan.live==true)
				{
					g.fillRect(didan.x, didan.y,2,2);
				}
				else
				{
					en.ss.remove(didan);
				}
			}
		}
		for(int i=1;i<=3;i++)
		{
		this.drawtank(410,70*i,g,1,i);
		g.setColor(Color.BLACK);
		g.drawString("*", 435, 70*i+20);
		g.drawString("Õ½¼¨Í³¼Æ", 420, 35);
		}
		switch(c)
		{
		case 0:
			g.drawString("0", 440, 90);break;
		case 1:
			g.drawString("1", 440, 90);break;
		case 2:
			g.drawString("2", 440, 90);break;
		case 3:
			g.drawString("3", 440, 90);break;
		case 4:
			g.drawString("4", 440, 90);break;
		case 5:
			g.drawString("5", 440, 90);break;
		}
		switch(b)
		{		
		case 0:
			g.drawString("0", 440, 160);break;
		case 1:
			g.drawString("1", 440, 160);break;
		case 2:
			g.drawString("2", 440, 160);break;
		case 3:
			g.drawString("3", 440, 160);break;
		case 4:
			g.drawString("4", 440, 160);break;
		case 5:
			g.drawString("5", 440, 160);break;
		}
		switch(a)
		{
		case 0:
			g.drawString("0", 440, 230);break;
		case 1:
			g.drawString("1", 440, 230);break;
		case 2:
			g.drawString("2", 440, 230);break;
		case 3:
			g.drawString("3", 440, 230);break;
		case 4:
			g.drawString("4", 440, 230);break;
		case 5:
			g.drawString("5", 440, 230);break;
		}
		g.setColor(Color.GRAY);
		g.fillRect(110,230,180,20);
		}
	public void keyPressed(KeyEvent e)
		{
		if(e.getKeyChar()=='w'&&this.hero.getY()>0)
		{
			this.hero.setdirect(1);
			this.hero.moveup();
		}
		else if(e.getKeyChar()=='s'&&this.hero.getY()<270)
		{
			this.hero.setdirect(2);
			this.hero.movedown();
		}
		else if(e.getKeyChar()=='a'&&this.hero.getX()>0)
		{
			this.hero.setdirect(3);
			this.hero.moveleft();
		}
		else if(e.getKeyChar()=='d'&&this.hero.getX()<370)
		{
			this.hero.setdirect(4);
			this.hero.moveright();
		}
		if(e.getKeyChar()=='j')
		{
			if(this.hero.ss.size()<5)
			{
			this.hero.shot(this.hero.getX(),this.hero.getY());
			}
		}
		if(e.getKeyChar()=='k')
		{
			star=!star;
		}
		this.repaint();
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void hittank(dan s,diren d)
	{
		switch(d.getdirect())
		{
		case 1:
		case 2:
			if(s.getX()>d.getX()&&s.getX()<d.getX()+20&&s.getY()>d.getY()&&s.getY()<d.getY()+30)
			{
				s.live=false;
				d.type--;
				if(d.type==0)
				{
					d.live=false;
				}
			}
			break;
		case 3:
		case 4:
			if(s.getX()>d.getX()&&s.getX()<d.getX()+30&&s.getY()>d.getY()&&s.getY()<d.getY()+20)
			{
				s.live=false;
				d.type--;
				if(d.type==0)
				{
					d.live=false;
				}
			}
			break;
		}
	}
	public void qiang()
	{
		for(int i=0;i<di.size();i++)
		{
			diren et=this.di.get(i);
			for(int j=0;j<et.ss.size();j++)
			{
				dan dan=et.ss.get(j);
					if(dan.x>110&&dan.x<290&&dan.y>230&&dan.y<250)
					{
						dan.live=false;
					}
			}
		}
	}
	
	public void hitme()
	{
		for(int i=0;i<di.size();i++)
		{
			diren et=this.di.get(i);
			for(int j=0;j<et.ss.size();j++)
			{
				dan dan=et.ss.get(j);
				if(dan.x>180&&dan.x<220&&dan.y>260&&dan.y<300)
				{
					dan.live=false;
					hero.live=false;
				}
				switch(hero.direct)
				{
				case 1:
				case 2:
					if(dan.x>hero.x&&dan.x<hero.x+20&&dan.y>hero.y&&dan.y<hero.y+30)
					{
						hero.live=false;
						dan.live=false;
					}
				case 3:
				case 4:
					if (dan.x>hero.x&&dan.x<hero.x+30&&dan.y>hero.y&&dan.y<hero.y+30)
					{
						hero.live=false;
						dan.live=false;
					}
				}
			}
		}
			for(int i=0;i<hero.ss.size();i++)
			{
				dan dan=hero.ss.get(i);
				if(dan.x>180&&dan.x<220&&dan.y>260&&dan.y<300)
				{
						hero.live=false;
						dan.live=false;
				}
			}
	}
	public void drawdan(Graphics g)
	{
		for (int i=0;i<this.hero.ss.size();i++)
		{
			dan mydan=this.hero.ss.get(i);
			if(mydan!=null&&mydan.live==true)
			{
				g.fillRect(mydan.x,mydan.y, 3,3);
			}
			if(mydan.live==false)
			{
				this.hero.ss.remove(mydan);
			}
		}
	}

	public void drawtank(int x,int y,Graphics g,int direction,int type)
	{

		switch (type)
		{
		case 0:
			g.setColor(Color.YELLOW);break;
		case 1:
			g.setColor(Color.CYAN);break;
		case 2:
			g.setColor(Color.ORANGE);break;
		case 3:
			g.setColor(Color.RED);break;
		}
		switch (direction)
		{
		case 1:
			g.fill3DRect(x, y, 5, 30,false);
			g.fill3DRect(x+5,y+5,10,22,false);
			g.fill3DRect(x+15, y, 5,30,false);
			g.fillOval(x+5, y+10, 8, 8);
			g.drawLine(x+10,y,x+10,y+15);
			break;
		case 2:
			g.fill3DRect(x, y, 5, 30,false);
			g.fill3DRect(x+5,y+5,10,22,false);
			g.fill3DRect(x+15, y, 5,30,false);
			g.fillOval(x+5, y+10, 8,8);
			g.drawLine(x+10,y+30,x+10,y+15);
			break;
		case 3:
			g.fill3DRect(x, y,30,5,false);
			g.fill3DRect(x+5,y+5,22,10,false);
			g.fill3DRect(x, y+15,30,5,false);
			g.fillOval(x+10, y+5, 8,8);
			g.drawLine(x,y+10,x+15,y+10);
			break;
		case 4:
			g.fill3DRect(x, y,30,5,false);
			g.fill3DRect(x+5,y+5,22,10,false);
			g.fill3DRect(x, y+15,30,5,false);
			g.fillOval(x+10, y+5, 8,8);
			g.drawLine(x+30,y+10,x+15,y+10);
			break;
	}
}
	public void count()
	{
		for(int i=0;i<this.di.size();i++)
		{
			diren d=this.di.get(i);
			if(d.live==false&&d.age==true)
			{
				switch (d.flag)
				{
				case 1:
					c++;break;
				case 2:
					b++;break;
				case 3:
					a++;break;
				}
				d.age=false;
			}
		}
		
}
	public void run()
	{
		while(true)
		{
		try{
			Thread.sleep(100);
		}
		catch(Exception e){}
			for(int i=0;i<this.hero.ss.size();i++)
			{
				dan mydan=this.hero.ss.get(i);
				if(mydan.live==true)
				{
					for(int j=0;j<this.di.size();j++)
					{
						diren d=this.di.get(j);
						if(d.live==true)
						this.hittank(mydan,d);

					}
				}
			}
			this.hitme();
			this.repaint();
			this.count();
			this.qiang();
		}
	}
}
class Atank
{
	int x;
	int y;
	int speed=2;
	int direct=2;
	
	public int getdirect() {
		return direct;
	}
	public void setdirect(int direct) {
		this.direct = direct;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public Atank(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	public void moveup()
	{
		this.y-=speed;
	}
	public void movedown()
	{
		this.y+=speed;
	}
	public void moveleft()
	{
		this.x-=speed;
	}
	public void moveright()
	{
		this.x+=speed;
	}
}
class diren extends Atank implements Runnable //////////diren!!!!!
{
	boolean star=true;
	dan s;
	int type=3;
	int flag;
	boolean age=true;
	Vector<dan> ss=new Vector<dan>();
	boolean live=true;
	public diren(int x,int y,int type)

	{
		super(x,y);
		this.type=type;
		flag=type;
	}

	public void run()
	{
		while(true)
		{
			this.direct=(int)(Math.random()*4+1); 
				try{
					Thread.sleep(50);
					}
				catch(Exception e){}
				switch (this.direct)
				{
				case 1:
				{
					for(int i=0;i<20;i++)
					{
					if(y>0)
					y-=speed;
					try{
						Thread.sleep(20);
						}
					catch(Exception e){}
					}
					break;
				}
				case 2:
				{
					for(int i=0;i<20;i++)
					{
					if(y<365)
					y+=speed;
					try{
						Thread.sleep(20);
						}
					catch(Exception e){}
					}				
					break;
				}
				case 3:
				{
					for(int i=0;i<20;i++)
					{
					if(x>0)
					x-=speed;
					try{
						Thread.sleep(20);
						}
					catch(Exception e){}
					}
					break;
				}
				case 4:
				{
					for(int i=0;i<20;i++)
					{
					if(x<270)
					x+=speed;
					try{
						Thread.sleep(20);
						}
					catch(Exception e){}
					}
					break;
				}
				}		
				if(this.live==false)
				{
					break;
				}
				if(this.ss.size()<1)
				{
					switch (this.direct)
					{
					case 1:
						s=new dan(x+10,y,1);
						ss.add(s);
						break;
					case 2:
						s=new dan(x+10,y+30,2);
						ss.add(s);
						break;
					case 3:
						s=new dan(x,y+10,3);
						ss.add(s);
						break;
					case 4:
						s=new dan(x+30,y+10,4);
						ss.add(s);
						break;
					}
				}
				Thread t=new Thread(s);
				t.start();
			}
		}
}
class hero extends Atank				///////////////////hero!!!!
{
	Vector<dan> ss=new Vector<dan>();
	dan s=null;
	boolean live=true;
	public void shot(int x,int y)
	{
	switch (this.direct)
	{
	case 1:
		s=new dan(x+10,y,1);
		ss.add(s);
		break;
	case 2:
		s=new dan(x+10,y+30,2);
		ss.add(s);
		break;
	case 3:
		s=new dan(x,y+10,3);
		ss.add(s);
		break;
	case 4:
		s=new dan(x+30,y+10,4);
		ss.add(s);
		break;
	}
	Thread t=new Thread(s);
	t.start();
	}
	public hero(int x,int y)
	{
		super(x,y);
	}
}

class dan implements Runnable
{
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	int x;
	int y;
	int fang;
	int sudu=4;
	public int getSudu() {
		return sudu;
	}
	public void setSudu(int sudu) {
		this.sudu = sudu;
	}
	boolean live=true;
	public dan(int x,int y,int fang)
	{
		this.x=x;
		this.y=y;
		this.fang=fang;
	}
	public void run()
	{
		while (true)
		{
			try{
				Thread.sleep(50);
			}
			catch(Exception e){}
		switch (fang)
		{
		case 1:
			y-=sudu;break;
		case 2:
			y+=sudu;break;
		case 3:
			x-=sudu;break;
		case 4:
			x+=sudu;break;
		}
		if(x<-1||x>400||y<-1||y>300)
		{
			live=false;
			break;
		}
		}
	}
}