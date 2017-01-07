import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Ruzan on 10/14/2016.
 */
public class Zipping
{
    String infile;
    String opfile;
    public Zipping(String ifile)
    {
        infile = ifile;
    }
    public void ziporunzip()throws IOException
    {
        if(infile.substring(infile.length()-2,infile.length()).equals(".Z"))
        {
            opfile = infile + ".uc";
            unzipit();
        }
        else
        {
            opfile = infile + ".Z";
            zipit();
        }
    }
    private void unzipit()
    {
        byte buffer[] = new byte[1024];
        int l;
        try (GZIPInputStream gop = new GZIPInputStream(new FileInputStream(infile));
             FileOutputStream fi = new FileOutputStream(opfile);)
        {
            while((l = gop.read(buffer)) > 0)
            {
                fi.write(buffer,0,l);
            }
            System.out.println("File " + infile + " unzipped as " + opfile);
            gop.close();
            fi.close();
        }
        catch(IOException e)
        {
            System.out.println("File " + infile + " could not be unzipped due to the bellow error.");
            e.printStackTrace();
        }
    }
    private void zipit()throws IOException
    {
        byte buffer[] = new byte[1024];
        int l;
        try (GZIPOutputStream gop = new GZIPOutputStream(new FileOutputStream(opfile));
             FileInputStream fi = new FileInputStream(infile);)
        {
            while((l = fi.read(buffer)) > 0)
            {
                System.out.println();
                gop.write(buffer,0,l);
            }
            System.out.println("File " + infile + " zipped as " + opfile);
            gop.finish();
            gop.close();
            fi.close();
        }
        catch(IOException e)
        {
            System.out.println("File " + infile + " could not be zipped due to the bellow error.");
            e.printStackTrace();
        }
    }
    public static void main(String ag[]) throws IOException
    {
        String file =  "xyz.txt.Z ";
        if(ag.length > 0)
        {
            file = ag[0];
        }
        Zipping z = new Zipping(file);
        z.ziporunzip();
    }
}
