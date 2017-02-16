package it.marco;

import java.io.File;

import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.ID3Tag;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.MediaFile;
import org.blinkenlights.jid3.v1.ID3V1_0Tag;
import org.blinkenlights.jid3.v2.ID3V2_3_0Tag;

public class ID3Test {

	private static void main(String[] args)
    throws Exception
{
    // the file we are going to read
    File oSourceFile = new File("some_file.mp3");

    // create an MP3File object representing our chosen file
    MediaFile oMediaFile = new MP3File(oSourceFile);

    // any tags read from the file are returned, in an array, in an order which you should not assume
    ID3Tag[] aoID3Tag = oMediaFile.getTags();
    // let's loop through and see what we've got
    // (NOTE:  we could also use getID3V1Tag() or getID3V2Tag() methods, if we specifically want one or the other)
    for (int i=0; i < aoID3Tag.length; i++)
    {
        // check to see if we read a v1.0 tag, or a v2.3.0 tag (just for example..)
        if (aoID3Tag[i] instanceof ID3V1_0Tag)
        {
            ID3V1_0Tag oID3V1_0Tag = (ID3V1_0Tag)aoID3Tag[i];
            // does this tag happen to contain a title?
            if (oID3V1_0Tag.getTitle() != null)
            {
                System.out.println("Title = " + oID3V1_0Tag.getTitle());
            }
            // etc.
        }
        else if (aoID3Tag[i] instanceof ID3V2_3_0Tag)
        {
            ID3V2_3_0Tag oID3V2_3_0Tag = (ID3V2_3_0Tag)aoID3Tag[i];
            // check if this v2.3.0 frame contains a title, using the actual frame name
            if (oID3V2_3_0Tag.getTIT2TextInformationFrame() != null)
            {
                System.out.println("Title = " + oID3V2_3_0Tag.getTIT2TextInformationFrame().getTitle());
            }
            // but check using the convenience method if it has a year set (either way works)
            try
            {
                System.out.println("Year = " + oID3V2_3_0Tag.getYear());  // reads TYER frame
            }
            catch (ID3Exception e)
            {
                // error getting year.. if one wasn't set
                System.out.println("Could get read year from tag: " + e.toString());
            }
            // etc.
        }
    }
}
}
