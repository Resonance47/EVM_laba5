package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.sound.midi.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import javax.swing.JFrame;


class Piano extends JComponent implements KeyListener, MouseListener
{
	static int vol = 100;
	static int[] codes = new int[131];
	BufferedImage _image;
	BufferedImage _druz;
	private int pedal = 0;
	MidiChannel obj;
	static int shift = 48;
	ArrayList<Integer> notes;

	private int mouse_note(int x, int y)
	{
		if (y > 213)
		{
			int i = 0;
			if (x < 1582)
			{
				i++;
			}

			if (x < 1475)
			{
				i++;
				i++;
			}
			if (x < 1417)
			{
				i++;
				i++;
			}
			if (x < 1362)
			{
				i++;
				i++;
			}
			if (x < 1308)
			{
				i++;
			}
			if (x < 1254)
			{
				i++;
				i++;
			}
			if (x < 1200)
			{
				i++;
				i++;
			}
			if (x < 1145)
			{
				i++;
			}

			if (x < 1089)
			{
				i++;
				i++;
			}
			if (x < 1033)
			{
				i++;
				i++;
			}
			if (x < 979)
			{
				i++;
				i++;
			}
			if (x < 923)
			{
				i++;
			}
			if (x < 868)
			{
				i++;
				i++;
			}
			if (x < 814)
			{
				i++;
				i++;
			}
			if (x < 759)
			{
				i++;
			}

			if (x < 704)
			{
				i++;
				i++;
			}
			if (x < 647)
			{
				i++;
				i++;
			}
			if (x < 594)
			{
				i++;
				i++;
			}
			if (x < 538)
			{
				i++;
			}
			if (x < 484)
			{
				i++;
				i++;
			}
			if (x < 430)
			{
				i++;
				i++;
			}
			if (x < 370)
			{
				i++;
			}

			if (x < 318)
			{
				i++;
				i++;
			}
			if (x < 263)
			{
				i++;
				i++;
			}
			if (x < 207)
			{
				i++;
				i++;
			}
			if (x < 152)
			{
				i++;
			}
			if (x < 96)
			{
				i++;
				i++;
			}
			if (x < 42)
			{
				i++;
				i++;
			}


			return (96 - i);

		} else
		{
			int i = 94;

			if (x < 1430)
			{
				i -= 2;
			}
			if (x < 1366)
			{
				i -= 2;
			}
			if (x < 1273)
			{
				i -= 2;
				i--;
			}
			if (x < 1208)
			{
				i -= 2;
			}


			if (x < 1110)
			{
				i -= 2;
				i--;
			}
			if (x < 1046)
			{
				i -= 2;
			}
			if (x < 983)
			{
				i -= 2;
			}
			if (x < 888)
			{
				i -= 2;
				i--;
			}
			if (x < 822)
			{
				i -= 2;
			}

			if (x < 724)
			{
				i -= 2;
				i--;
			}
			if (x < 660)
			{
				i -= 2;
			}
			if (x < 598)
			{
				i -= 2;
			}


			if (x < 503)
			{
				i -= 2;
				i--;
			}
			if (x < 438)
			{
				i -= 2;
			}
			if (x < 338)
			{
				i -= 2;
				i--;
			}
			if (x < 274)
			{
				i -= 2;
			}
			if (x < 214)
			{
				i -= 2;
			}
			if (x < 117)
			{
				i -= 2;
				i--;
			}
			if (x < 54)
			{
				i -= 2;
			}
			return (i);
		}
	}

	public void paint(Graphics g)
	{
		g.drawImage(this._image, 0, 0, null);
		g.drawImage(this._druz, 0, 303, null);
		//g.drawLine(1, 1, 100, 100);
	}

	Piano(MidiChannel channel) throws IOException
	{
		this._image = ImageIO.read(new File("C:\\java\\1.jpg"));
		//this._druz = ImageIO.read(new File("C:\\java\\3.png"));
		this._druz = ImageIO.read(new File("C:\\java\\2.jpg"));
		//addKeyListener(this);
		for (int i = 0; i < 131; i++)
		{
			codes[i] = 0;
		}

		codes[81] = 0;//ut
		codes[50] = 1;//#
		codes[87] = 2;//re
		codes[51] = 3;//#
		codes[69] = 4;//mi
		codes[82] = 5;//fa
		codes[53] = 6;//#
		codes[84] = 7;//sol
		codes[54] = 8;//#
		codes[89] = 9;//lya
		codes[55] = 10;//#
		codes[85] = 11;//
		codes[90] = 12;//ut
		codes[83] = 13;//#
		codes[88] = 14;//re
		codes[68] = 15;//#
		codes[67] = 16;//mi
		codes[86] = 17;//fa
		codes[71] = 18;//#
		codes[66] = 19;//sol
		codes[72] = 20;//#
		codes[78] = 21;//lya
		codes[74] = 22;//#
		codes[77] = 23;//

		notes = new ArrayList<>();
		obj = channel;
		System.out.println("Init complete");
	}

	public void setPedal(int status)
	{
		if(pedal == 1 && status == 0)
		{
			System.out.println("Pedal off!");
		}
		else if (pedal == 0 && status == 1)
		{
			System.out.println("Pedal on!");
		}
		pedal = status;
	}

	private void play(int note, int vol)
	{
		if (!notes.contains(note))
		{
			System.out.println("Note's code: " + note);
			obj.noteOn(note, vol);
			notes.add(note);
		}
	}

	private void stop(int note)
	{
		if (pedal == 0)
		{
			obj.noteOff(note);
		}
		//remove(note);
		for (int i = 0; i < notes.size(); i++)
		{
			if (notes.get(i) == note)
			{
				notes.remove(i);
				break;
			}
		}
	}

	public void clear()
	{
		for (int i = 0; i < 131; i++)
		{
			obj.noteOff(i);
		}
		notes.clear();
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int note = e.getKeyCode();
		if (note == 32)
		{
			setPedal(1);
		}
		else if (note == KeyEvent.VK_ALT)
		{
			try
			{
				Melody.start(this);
			} catch (InterruptedException interruptedException)
			{
				interruptedException.printStackTrace();
			}

		} else if (note == KeyEvent.VK_ENTER)
		{
			try
			{
				Classic.start(this);
			} catch (InterruptedException interruptedException)
			{
				interruptedException.printStackTrace();
			}
		} else
		{
			note = codes[note] + shift;
			play(note, vol);
		}

	}

	@Override
	public void keyReleased(KeyEvent e)
	{

		int note = e.getKeyCode();
		if (note == 32)
		{
			setPedal(0);
			clear();
		} else
		{
			note = codes[note] + shift;
			stop(note);
		}
	}


	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		play(mouse_note(e.getX(), e.getY()), vol);
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		stop(mouse_note(e.getX(), e.getY()));
		//System.out.println(e.getX());
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}

	public void play_accord(int x1, int x2, int x3, int x4)
	{
		play(x1, vol);
		play(x2, vol);
		play(x3, vol);
		play(x4, vol);
	}

	public void stop_accord(int x1, int x2, int x3, int x4)
	{
		stop(x1);
		stop(x2);
		stop(x3);
		stop(x4);
	}

	public void p(int x, int vol)
	{
		s(x);
		play(x, vol);
	}

	public void s(int x)
	{
		stop(x);
	}
}

class Melody
{
	static Timer timer;

	//static Piano piano;
	public static void start(Piano piano) throws InterruptedException
	{
		int d1 = 650;
		int d2 = 450;
		int d3 = 210;
		//си и ми бемоль
		// минор
		timer = new Timer();
		//piano.pedal = 1;
		piano.play_accord(67, 62, 43, 58);
		Thread.sleep(d1);
		piano.stop_accord(67, 62, 58, 43);
		piano.play_accord(67, 62, 58, 43);
		Thread.sleep(d1);
		piano.stop_accord(67, 62, 58, 43);
		piano.play_accord(67, 62, 58, 43);
		Thread.sleep(d1);
		piano.stop_accord(67, 62, 58, 43);
		piano.play_accord(63, 58, 54, 39);
		Thread.sleep(d2);
		piano.play_accord(70, 70, 70, 70);
		Thread.sleep(d3);
		piano.stop_accord(63, 58, 54, 39);
		piano.stop_accord(70, 70, 70, 70);
		piano.play_accord(67, 62, 58, 43);
		Thread.sleep(d1);
		piano.stop_accord(67, 62, 58, 43);
		piano.play_accord(63, 58, 54, 39);
		Thread.sleep(d2);
		piano.play_accord(70, 70, 70, 70);
		Thread.sleep(d3);
		piano.stop_accord(63, 58, 54, 39);
		piano.stop_accord(70, 70, 70, 70);
		piano.play_accord(67, 62, 58, 43);
		Thread.sleep(2 * d1);

		piano.stop_accord(67, 62, 58, 43);
		piano.play_accord(74, 70, 67, 43);
		Thread.sleep(d1);
		piano.stop_accord(74, 70, 67, 43);
		piano.play_accord(74, 70, 67, 43);
		Thread.sleep(d1);
		piano.stop_accord(74, 70, 67, 43);
		piano.play_accord(74, 70, 67, 43);
		Thread.sleep(d1);
		piano.stop_accord(74, 70, 67, 43);
		piano.play_accord(75, 70, 66, 39);

		Thread.sleep(d2);
		piano.stop_accord(70, 0, 0, 0);
		piano.play_accord(70, 70, 70, 70);
		Thread.sleep(d3);
		piano.stop_accord(75, 70, 66, 39);
		piano.stop_accord(70, 70, 70, 70);
		piano.play_accord(66, 63, 58, 39);
		Thread.sleep(d1);
		piano.stop_accord(66, 63, 58, 39);
		piano.play_accord(63, 58, 54, 39);
		Thread.sleep(d2);
		piano.play_accord(70, 70, 70, 70);
		Thread.sleep(d3);
		piano.stop_accord(63, 58, 54, 39);
		piano.stop_accord(70, 70, 70, 70);
		piano.clear();
		piano.play_accord(67, 62, 58, 43);
		Thread.sleep(2 * d1);


/*
		piano.stop_accord(67, 62, 58, 43);
		piano.play_accord(79, 70, 74, 43);
		Thread.sleep(d1);
		piano.stop_accord(79, 70, 74, 43);
		piano.play_accord(67, 67, 67, 43);
		Thread.sleep(d2);
		piano.stop_accord(67, 67, 67, 67);
		piano.play_accord(67, 67, 67, 67);
		Thread.sleep(d3);
		piano.stop_accord(67, 67, 67, 43);
		piano.play_accord(79, 70, 74, 43);
		Thread.sleep(d1);
		piano.stop_accord(79, 70, 74, 43);
		piano.play_accord(78, 70, 74, 43);
		Thread.sleep(d2);
		piano.stop_accord(78, 78, 78, 78);
		piano.play_accord(78, 78, 78, 78);
		Thread.sleep(d3);
		piano.stop_accord(78, 78, 78, 43);
		piano.stop_accord(78, 70, 74, 74);
		piano.play_accord(68, 73, 76, 36);
		Thread.sleep(d3);
		piano.stop_accord(68, 73, 76, 76);
		piano.play_accord(75, 75, 75, 75);
		Thread.sleep(d3);
		piano.stop_accord(75, 75, 75, 75);
		piano.play_accord(68, 73, 76, 76);
		Thread.sleep(d2);
		piano.stop_accord(68, 73, 76, 36);
		piano.play_accord(36, 36, 36, 36);
		Thread.sleep(d2);*/


	}
}

class Classic
{
	//бемоль си, ми, ля
	static int d1 = 500;
	static int d2 = 250;
	static int d3 = 500;

	static int q1 = 80;
	static int q2 = 40;
	static int o = 12;

	public static void start(Piano p) throws InterruptedException
	{
		p.setPedal(1);

		p.p(62 + o, q2);
		p.p(65 + o, q2);
		p.p(58 - o, q1);
		p.p(58 + o, q1);
		p.p(58, q1);
		Thread.sleep(d1);
		p.p(64 + o, q1);
		p.p(67 + o, q1);
		Thread.sleep(d1);

		p.clear();
		p.p(65 + o, q1);
		p.p(68 + o, q1);
		p.p(53, q1);

		Thread.sleep(d2);
		p.p(56, q1 - 50);
		Thread.sleep(d2);
		p.p(60, q1 - 50);
		Thread.sleep(d2);
		p.p(56, q1 - 50);
		Thread.sleep(d2);
		p.p(65, q1);
		Thread.sleep(d2);

		p.p(48, q1);
		p.p(60, q1);
		p.s(65 + o);
		p.p(65 + o, q1);
		Thread.sleep(d1);
		p.p(67 + o, q1);
		Thread.sleep(d1);
		p.clear();


		p.setPedal(1);

		p.p(68 + o, q1);
		p.p(65 + o, q1);
		p.p(41, q1);
		Thread.sleep(d2);
		p.p(56, q1 - 50);
		Thread.sleep(d2);
		p.p(60 + o, q1 - 50);
		Thread.sleep(d2);
		p.p(56, q1 - 50);
		Thread.sleep(d1);
		//p.clear();
		p.p(44, q1);
		//p.p(56, q1);
		p.p(67 + o, q1);
		Thread.sleep(d1);
		p.s(65 + o);
		p.s(60 + o);
		p.p(65 + o, q1);
		p.p(60 + o, q1);
		Thread.sleep(d1);
		p.clear();

		p.p(67 + o, q1);
		p.p(48, q1);
		Thread.sleep(d2);

		p.p(55, q1);
		Thread.sleep(d1);
		p.p(60 + o, q1);
		Thread.sleep(d2);
		p.p(65, q1);
		Thread.sleep(d3);
		p.clear();
		p.setPedal(0);
		p.setPedal(1);
		p.p(63 + o, q1);
		p.p(43, q1);
		p.p(55, q1);
		Thread.sleep(d2);
		p.s(48);
		p.p(48, q1);
		Thread.sleep(d2);
		p.p(55, q2);
		Thread.sleep(d2);
		p.p(60 + o, q2);
		Thread.sleep(d2);
		p.p(63 + o, q2);
		Thread.sleep(d2);
		//p.clear();

		p.p(62 + o, q1);
		p.p(55, q1);

		Thread.sleep(d1);
		p.p(60 + o, q1);
		p.p(63 + o, q1);

		Thread.sleep(d1);

		p.clear();
		p.p(65 + o, q1);
		p.p(50, q1);
		Thread.sleep(d1);
		p.p(55, q2);
		Thread.sleep(d2);
		p.p(59 + o, d2);
		Thread.sleep(d2);
		p.p(65 + o, q2);
		Thread.sleep(d2);
		p.p(55, q2);
		Thread.sleep(d2);
		p.p(50, q2);
		Thread.sleep(d2);
		p.p(50, q2);
		Thread.sleep(d2);
		p.p(55, q2);
		Thread.sleep(d2);
		p.p(62 + o, q2);
		Thread.sleep(d2);
		p.p(55, q2);
		Thread.sleep(d2);
		p.p(60 + o, q1);
		p.p(43, q1);
		Thread.sleep(d2);
		p.p(55, q2);
		Thread.sleep(d2);
		p.p(59 + o, q1);
		p.p(62 + o, q1);
		Thread.sleep(d1);
		p.clear();

		p.p(63 + o, q2);
		p.p(48, q1);


		Thread.sleep(d2);
		p.p(55, q2);
		Thread.sleep(d2);
		p.p(60 + o, q2);
		Thread.sleep(d2);
		p.p(67 + o, q2);
		Thread.sleep(d2);
		p.p(43, q2);
		Thread.sleep(d2);
		p.p(55, q2);
		Thread.sleep(d2);
		p.p(48, q2);
		p.p(64 + o, q2);
		Thread.sleep(d2);
		p.p(55, q2);
		Thread.sleep(d2);
		p.p(58 + o, q2);
		Thread.sleep(d2);
		p.p(60 + o, q2);
		Thread.sleep(d2);
		p.p(50, q1);
		p.p(65 + o, q1);
		Thread.sleep(d1);
		p.p(51, q1);
		p.p(67 + o, q1);


		Thread.sleep(d2);
		p.clear();


		p.p(65 + o, q1);
		p.p(68 + o, q1);
		p.p(53, q1);

		Thread.sleep(d2);
		p.p(56, q1 - 50);
		Thread.sleep(d2);
		p.p(60, q1 - 50);
		Thread.sleep(d2);
		p.p(56, q1 - 50);
		Thread.sleep(d2);
		p.p(65, q1);
		Thread.sleep(d2);

		p.p(48, q1);
		p.p(60 + o, q1);
		p.p(65 + o, q1);
		Thread.sleep(d1);
		p.p(67 + o, q1);
		Thread.sleep(d1);
		p.clear();


		p.p(68 + o, q1);
		p.p(65 + o, q1);
		p.p(41, q1);

		Thread.sleep(d2);
		p.p(56, q1 - 50);
		Thread.sleep(d2);
		p.p(60 + o, q1 - 50);



		Thread.sleep(d2);
		p.p(56, q1 - 50);
		Thread.sleep(d1);
		p.p(44, q1);
		p.p(67 + o, q1);
		Thread.sleep(d1);
		p.s(65 + o);


		p.s(60 + o);
		p.p(65 + o, q1);
		p.p(60 + o, q1);
		Thread.sleep(d1);
		p.clear();


		p.p(67 + o, q1);
		p.p(48, q1);

		Thread.sleep(d2);
		p.p(55, q1);
		Thread.sleep(d2);
		p.p(60 + o, q1);
		System.out.println("Тект просто так :)");
		Thread.sleep(d2);
		p.p(65, q1);
		Thread.sleep(d3);
		p.clear();
		p.p(63 + o, q1);
		p.p(43, q1);
		p.p(55, q1);
		Thread.sleep(d2);
		p.s(48);
		p.p(48, q1);
		Thread.sleep(d2);
		p.p(55, q2);
		Thread.sleep(d2);
		p.p(60 + o, q2);
		Thread.sleep(d2);
		p.p(63 + o, q2);
		Thread.sleep(d2);
		//p.clear();


		p.p(62 + o, q1);
		p.p(55, q1);

		Thread.sleep(d1);
		p.p(60 + o, q1);
		p.p(63 + o, q1);

		Thread.sleep(d1);
		p.clear();
		p.p(65 + o, q1);
		p.p(50, q1);
		Thread.sleep(d2);
		p.p(55, q1);
		Thread.sleep(d2);
		p.p(59 + o, q2);
		Thread.sleep(d2);
		p.p(56, q2);
		Thread.sleep(d2);

		p.p(62 + o, q2);
		p.p(43, q1);
		Thread.sleep(d1);
		p.p(55, q2);
		Thread.sleep(d3);
		p.p(50, q2);
		Thread.sleep(d2);
		p.p(55, q2);
		Thread.sleep(d2);
		p.p(59 + o, q2);
		Thread.sleep(d2);
		p.p(65 + o, q2);
		Thread.sleep(d2);

		p.p(63 + o, q1);
		p.p(43, q1);
		//p.p(55, q2);
		Thread.sleep(d2);
		p.p(55, q1);

		Thread.sleep(d2);
		p.p(62 + o, q1);
		p.p(59 + o, q1);
		Thread.sleep(d1);
		p.clear();
		p.setPedal(1);
		Thread.sleep(1);
		p.p(60 + o, q1);
		p.p(48, q1);
		Thread.sleep(1000);
	}
}

public class Main
{
	public static void main(String[] args) throws MidiUnavailableException, InterruptedException, IOException
	{
		JFrame frame = new JFrame();
		frame.setSize(1520, 600);//2000 x 340
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setTitle("Analysis");
		frame.setLocation(5, 5);
		frame.setTitle("Внимание, пеонина!");
		//MYP myp = new MYP();
		Sequencer sequencer = MidiSystem.getSequencer();
		sequencer.open();
		Synthesizer synth = MidiSystem.getSynthesizer();
		synth.open();
		MidiChannel[] channels = synth.getChannels();
		MidiChannel synthChannel = channels[channels.length - 1];
		synthChannel.programChange(0);
		Piano piano = new Piano(synthChannel);
		frame.add(piano);
		frame.addKeyListener(piano);
		frame.repaint();
		frame.addMouseListener(piano);
		frame.setVisible(true);

		Thread.sleep(1000);

		//frame.repaint();
	}
}
