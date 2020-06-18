import React, { Component } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faList, faEdit } from '@fortawesome/free-solid-svg-icons';
import { ButtonGroup } from 'react-bootstrap';
import { Link } from 'react-router-dom';

class LibraryCollectionClient extends Component {

  constructor(props) {
    super(props);
    this.state = this.initialState;
    this.state = {
      bookData: [],
      libraryData: [],
      value: ''
    }
   
    
  }

  initialState = {
    name: '', genre: '', language: '', author: ''
  };

  componentDidMount(event) {

    let config = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    }
    fetch('http://localhost:9999/libraries', config)
      .then(response => response.json())
      .then(data => {
        this.setState({
          libraryData: data
        });
      })
      .catch(error => {
        console.log(error.message);
      })

  }



  onAddBookHandle(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    const libraryId = data.get('libraryId');
    let config = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        name: data.get('name'),
        genre: data.get('genre'),
        language: data.get('language'),
        author: data.get('author')
      })
    }
    console.log(config);
    fetch(`http://localhost:9999/libraries/${libraryId}/book`, config)
      .then(response => response.json())
      .then(data => {
        console.log(data);
        this.setState({
          bookData: [...this.state.bookData, {
            ...data
          }]
        });
      })
      .catch(error => {
        console.log(error.message);
      })

  }

  getAllBooks(e) {
    e.preventDefault();
    let config = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    }
    let libraryId = e.target.dataset.id
    console.log(libraryId);
    fetch(`http://localhost:9999/libraries/${libraryId}/books`, config)
      .then(response => response.json())
      .then(data => {
        this.setState({
          bookData: data
        });
      })
      .catch(error => {
        console.log(error.message);
      })
  }




  onEditHandler(e) {
    e.preventDefault();
    let config = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    }
    let bookId = e.target.dataset.id
    console.log(bookId);
    fetch(`http://localhost:9999/books/${bookId}`, config)
      .then(response => response.json())
      .then(data => {
        console.log(data);
        this.setState({
          name: data.name,
          genre: data.genre,
          language: data.language,
          author: data.author
        });
      }).catch((error) => {
        console.error("Error - " + error);
      });

  }

  handleChange = (e) =>{ 
    this.setState({name: e.target.value});
  }


  render() {

    const { name, genre, language, author } = this.state;
    return (
      <div>
        <ul>
          {this.state.libraryData.map(item => (
            <li key={item.libraryId} ><a data-id={item.libraryId} href="javascript:void(0);" onClick={this.getAllBooks.bind(this)}>{item.name}</a>
              <form onSubmit={this.onAddBookHandle.bind(this)}>
                <table className="tableClassTwo">
                  <tbody>
                    <tr>
                      <td><label>Name:</label></td>
                      <td><div><input type="text" value={this.state.name} onChange={this.handleChange}/>
                      </div></td>
                      <td><label>Genre:</label></td>
                      <td><input type="text" name={genre}/></td>
                    </tr>
                    <tr>
                      <td><label>Language:</label></td>
                      <td><input type="text" name="language" />{language}</td>
                      <td><label>Author:</label></td>
                      <td><input type="text" name="author" />{author}</td>
                    </tr>
                    <tr>
                      <td><button>Add Book</button></td>
                    </tr>
                    <tr><td><input type="hidden" name="libraryId" value={item.libraryId} /></td></tr>
                  </tbody>
                </table>

              </form>
            </li>
          ))}
        </ul>
        <table className="tableClass">
          <tbody>
            <tr>
              <th>Name : </th>
              <th>Genre : </th>
              <th>Language : </th>
              <th>Author : </th>
              <th>Actions : </th>
            </tr>
            {this.state.bookData.map(item => (
              <tr key={item.id}>
                <td> {item.name}</td>
                <td> {item.genre}</td>
                <td> {item.language}</td>
                <td> {item.author}</td>
                <td>
                  <div>
                    <a data-id={item.id} href="javascript:void(0);" onClick={this.onEditHandler.bind(this)}>Edit</a>
                  </div>

                  {/* <ButtonGroup>
                  <a to={"edit/" + item.id} className="btn btn-sm btn-outline-primary"><FontAwesomeIcon icon={faEdit} /></a>{' '}
                  </ButtonGroup> */}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default LibraryCollectionClient;
