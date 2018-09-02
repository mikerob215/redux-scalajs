const {bindActionCreators, createStore} = require('../../target/scala-2.12/redux-scala-js-opt');

const CLICKED = 'CLICKED';

const reducer = (state, action) => {
    switch (action.type) {
        case CLICKED:
            return {
                ...state,
                clicked: !state.clicked
            };
        default:
            return state;

    }
};

const store = createStore(reducer, {clicked: false});

store.subscribe(() => console.log(store.getState()));

const clicked = () => ({
    type: CLICKED
});

const dispatch = store.dispatch;

const bAcS = bindActionCreators({clicked}, dispatch);

bAcS.clicked();
bAcS.clicked();
bAcS.clicked();
bAcS.clicked();
bAcS.clicked();
bAcS.clicked();

