public class NickModel {
    private String nick;
    private NickModelListener listener;

    public NickModel() {
        this.nick = "";
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
        notifyListener();
    }

    public void setNickModelListener(NickModelListener listener) {
        this.listener = listener;
    }

    private void notifyListener() {
        if (listener != null) {
            listener.textChanged(nick);
        }
    }

    interface NickModelListener {
        void textChanged(String newNick);
    }
}
