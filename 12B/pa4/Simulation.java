// Simulation.java
// Nikolai Chen
// nmchen
// pa4
// Simulation reads a file, and creates jobs with arrival times
// and durations as they appear in the read file
// The jobs are then "processed" with a various number of processor queues
// ranging from 1 to n-1 where n is the number of jobs
// As they are processed they are printed to a trace file,
// while the total wait time, average wait time,
// and longest wait time are written to a report file.

import java.io.*;
import java.util.Scanner;

public class Simulation {

	public static void main(String[] args) throws IOException{

		Queue jobList = new Queue();
		Queue backupList = new Queue();
		Scanner in = null;
		String info;
		String[] data;
		int numJobs;
		int arrival;
		int duration;
		int time;
		int minLineSize;
		boolean jobFinish;
		boolean jobArrived;
		boolean allEmpty;
		int maxWait;
		int totalWait;
		double averageWait;

		PrintWriter report = new PrintWriter(new FileWriter(args[0] + ".rpt"));
		PrintWriter traceFile = new PrintWriter(new FileWriter(args[0] + ".trc"));

		// if there isn't an input file then the program exits
		if (args.length < 1) {
			System.out.println("Usage: Simulation <input file>");
			System.exit(1);
		}

		in = new Scanner(new File(args[0]));

		info = in.nextLine().trim();
		numJobs = Integer.parseInt(info);

		// turns each line of the input file into a job and enqueues it into the jobList and backupList
		while(in.hasNextLine()) {

			info = in.nextLine().trim();
			data = info.split("\\s");
			if (!data[0].equals("")) {
				arrival = Integer.parseInt(data[0]);
				duration = Integer.parseInt(data[1]);
				jobList.enqueue(new Job(arrival, duration));
				backupList.enqueue(new Job(arrival, duration));
			}

		}

		traceFile.println("Trace file: " + args[0] + ".trc\n" + numJobs + " Jobs:\n" + jobList.toString() + "\n");

		report.println("Report file: " + args[0] + ".rpt\n" + numJobs + " Jobs:\n" + jobList.toString() + "\n");

		report.println("*******************************");

		// processes jobs for 1 through numJobs-1 number of processors
		for (int count = 1; count < numJobs; count++) {

			traceFile.print("*******************************\n" + count + " processor");
			if (count > 1) {
				traceFile.print("s");
			}
			traceFile.print(":\n" + "*******************************\n");

			maxWait = 0;
			totalWait = 0;

			Queue[] processors = new Queue[count];
			Job front = (Job)jobList.peek();

			time = 0;

			for (int x = 0; x < processors.length; x++) {
				processors[x] = new Queue();
			}

			traceFile.println("time: " + time + "\n0: " + jobList);
			for (int x = 0; x < processors.length; x++) {
				int y = x + 1;
				traceFile.println(y + ": " + processors[x]);
				
			}
			traceFile.println();

			allEmpty = true;
			minLineSize = 0;

			// System.out.println(jobList);

			// processes through all jobs until all jobs are processed
			while(front == null || front.getFinish() == -1 || allEmpty == false) {

				jobFinish = false;
				jobArrived = false;
				Job done = null;


				// checks for finished job
				for (int x = 0; x < processors.length; x++) {

					Queue p = processors[x];

					if(!p.isEmpty()) {

						if (((Job)p.peek()).getFinish() == time) {
							jobFinish = true;
							done = (Job)processors[x].dequeue();
							jobList.enqueue(done);

							if (done == (Job)jobList.peek()) {
								front = done;
							}

							if (!processors[x].isEmpty()) {

								Job d = (Job)processors[x].peek();
								d.computeFinishTime(time);
								// System.out.println("I waited " + d.getWaitTime() + " light years");
								if (d.getWaitTime() > maxWait) {
									maxWait = d.getWaitTime();
								}
								totalWait += d.getWaitTime();

							}

							// System.out.println(done.toString());

						}

					}

				}

				// fixes minLineSize if job has been dequeued
				if (done != null) {

					for (int x = 0; x < processors.length; x++) {
						
						if(minLineSize > processors[x].length()) {

							minLineSize = processors[x].length();

						}

					}
					
				}

				// checks to see if a job has arrived
				while (front != null && !jobList.isEmpty() && front.getArrival() == time) {
					
					boolean placed = false;
					jobArrived = true;
					int i = 0;
					while(!placed) {
						if (processors[i].length() == minLineSize) {

							processors[i].enqueue(front);

							Job a = (Job)processors[i].peek();

							if (a == front) {
								
								a.computeFinishTime(time);

							}

							// System.out.println((Job)jobList.peek());
							jobList.dequeue();

							if (!jobList.isEmpty()) {
								front = (Job)jobList.peek();
							} else {
								front = null;
							}
							placed = true;

							for (int x = 0; x < processors.length; x++) {

								minLineSize = processors[x].length();

								if(minLineSize > processors[x].length()) {

									minLineSize = processors[x].length();

								}

							}

							// System.out.println("Line Size: " + minLineSize);

						} else {
							i++;
						}
					}

					// System.out.println(!jobList.isEmpty());

				}

				// if a job has been dequeued or enqueued then this prints out the new status of jobs
				if (jobFinish || jobArrived) {
					traceFile.println("time: " + time + "\n0: " + jobList.toString());
					// System.out.println("time: " + time + "\n0: " + jobList.toString());
					for (int x = 0; x < processors.length; x++) {
						int y = x + 1;
						traceFile.println(y + ": " + processors[x].toString());
						// System.out.println(y + ": " + processors[x].toString());
						
					}
					traceFile.println();
					// System.out.println();
				}

				// checks if all of the processors are empty or not
				int j = 0;
				do {
					allEmpty = processors[j].isEmpty();
					j++;
				}while(allEmpty && j < processors.length);

				time++;

				// System.out.println(jobList);
				// System.out.println("front == null: " + (front==null));
				// if (front != null) {
				// 	System.out.println("front.getFinish() == -1: " + (front.getFinish()==-1));	
				// }
				// System.out.println("allEmpty: " + allEmpty);


			}

			// prints to report file the longest wait, total wait, and average wait
			averageWait = (double)Math.round((double)totalWait/(double)numJobs * 100.0) / 100.0;
			report.println(count + " processors: totalWait = " + totalWait + ", maxWait = " + maxWait + ", averageWait = " + averageWait + "\n");

			// resets the jobList queue to it's original state

			Job bfront = (Job)backupList.peek();

			do {

				Job b = (Job)backupList.dequeue();
				b.resetFinishTime();
				jobList.enqueue(b);
				jobList.dequeue();
				backupList.enqueue(b);

			} while(backupList.peek() != bfront);

		}

		// System.out.println(numJobs);
		// System.out.println(backupList.toString());

		in.close();
		report.close();
		traceFile.close();


	}

}