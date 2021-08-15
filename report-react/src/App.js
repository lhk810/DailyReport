import React, {Component} from 'react';
import './App.css';
import TaskTable from './TaskTable'

class App extends Component {

  state= {

  }

  componentDidMount() {
    fetch('http://localhost:8080/api/task/')
    .then(res => res.json())
    .then((result) => {
      this.setState({ tasks : result });
    },
    (error) => {
      this.setState({
        error
      });
    })
  }

  _renderTable = () => {
    return <TaskTable tasks = {this.state.tasks}/>
  }

  render() {
    return (
        <div className= {TaskTable ? "App" : "App--loading"}>
          {this.state.tasks ? this._renderTable() :  'Loading'}
        </div>
    );
  }
}

export default App;
