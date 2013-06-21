package test;

import battleship.data.GameFile;
import battleship.logic.Gameplay;
import battleship.objects.User;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Unit test for data.GameFile
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class DataGameTest {

    private GameFile file = null;
    private String gid = null;

    /**
     * Create new file and a first gameplay.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        File store = new File("game.bin");
        store.delete();
        Gameplay gameplay = new Gameplay(new User(UUID.randomUUID().toString(), "Tom", "password1"));
        gid = gameplay.getGid();
        file = new GameFile();

        file.store(gameplay);
    }

    /**
     * Add another gameplay.
     *
     * @throws Exception
     */
    @Test
    public void testStore() throws Exception {
        Gameplay gameplay = new Gameplay(new User(UUID.randomUUID().toString(), "Alf", "Melmac"));
        file.store(gameplay);
    }

    /**
     * Try to get first gameplay.
     *
     * @throws Exception
     */
    @Test
    public void testGet() throws Exception {
        Gameplay gameplay = file.get(gid);
        assertEquals(gid, gameplay.getGid());
    }

    /**
     * Check multiple gameplays.
     *
     * @throws Exception
     */
    @Test
    public void testMultipleGames() throws Exception {
        Gameplay gameplay = new Gameplay(new User(UUID.randomUUID().toString(), "Hannibal", "Lector"));
        file.store(gameplay);

        Gameplay gameplay1 = file.get(gameplay.getGid());
        assertEquals(gameplay.getGid(), gameplay1.getGid());

        Gameplay gameplay2 = file.get(gid);
        assertEquals(gid, gameplay2.getGid());
    }
}
