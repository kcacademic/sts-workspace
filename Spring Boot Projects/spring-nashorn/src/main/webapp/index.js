var NameList = React.createClass({displayName: "NameList",
    render: function () {
        var nodes = this.props.data.map(function (x) {
            return (React.createElement("h2", null, x))
        });
        return (
            React.createElement("div", {className: "commentList"}, 
                nodes
            )
        );
    }
});


var App = React.createClass({displayName: "App",
	
    handleSubmit: function (comment) {
        var comments = this.state.data;
        console.log(comments)
        comments.push("Hi");
        this.setState({data: comments});
    },
    
    componentWillMount: function() {
    	this.setState({data: this.props.data});
    },
    
    componentDidMount: function() {
    	this.setState({data: this.props.data});
    },
	
    getInitialState: function () {
        return {data: []};
    },
	
    render: function () {
        return (
            React.createElement("div", {className: "app"},
            	React.createElement(NameList, {data: this.state.data}), 
                React.createElement("input", {type: "submit", value: "Post", onClick: this.handleSubmit})
            )     
        );
    }
});

var renderClient = function (comment) {
	ReactDOM.render(
        React.createElement(App, {data: comment}),
        document.getElementById("root")
    );
};

var renderServer = function (comment) {
	var comments = ["Kumar", "Chandrakant"]
    return ReactDOMServer.renderToString(
        React.createElement(App, {data: comments})
    );
};