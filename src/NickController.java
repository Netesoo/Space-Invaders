public class NickController {
    private NickModel model;
    public NickView view;

    public NickController(NickModel model, NickView view) {
        this.model = model;
        this.view = view;


        view.setNick(model.getNick());
        view.setDisplayText(model.getNick());

        view.addTextListener(newNick -> model.setNick(newNick));

        model.setNickModelListener(newNick -> {
            view.setDisplayText(newNick);
        });

    }
}
