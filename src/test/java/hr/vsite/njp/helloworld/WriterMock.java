package hr.vsite.njp.helloworld;

import hr.vsite.njp.helloworld.domain.Writer;

//nije potrebno jer se većina može napraviti Mockito library
public class WriterMock implements Writer {
    private final boolean raiseException;

    public WriterMock(boolean raiseException) {
        this.raiseException = raiseException;
    }

    @Override
    public void print(String message) {
        if (raiseException) {
            throw new RuntimeException();
        }
    }
}
