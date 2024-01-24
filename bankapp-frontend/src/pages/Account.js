import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

export default function Account() {

    function getCustNo() {
        const queryParams = new URLSearchParams(window.location.search)
        const value = queryParams.get('custNo');
      
        return value;
      }

    const [accounts, setAccounts]=useState([]);
    const [users, setUsers]=useState([]);

    useEffect(() => {
        loadUsers();
        loadAccounts();
    },[]);

    const loadAccounts= async ()=> {
        const result = await axios.get("http://localhost:8080/account/list/" + getCustNo());
        setAccounts(result.data);
    };

    const loadUsers= async ()=> {
        const result = await axios.get("http://localhost:8080/user/" + getCustNo());
        setUsers(result.data);
    };

  return (
    <div className='container'>
        <div className='py-4'>
            <table className="table border shadow">
                <thead>
                    <tr>
                    <th scope="col">Cust No</th>
                    <th scope="col">Cust Name</th>
                    <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        users.map((user,index)=>(
                            <tr>
                                <td>{user.custNo}</td>
                                <td>{user.custName}</td>
                                <td>
                                    <Link 
                                        className="btn btn-primary mx-2"
                                        to={`/addaccount/add?custNo=${user.custNo}`}
                                    >
                                        Add Account
                                    </Link>
                                </td>
                            </tr>
                        ))
                    }   
                </tbody>
            </table> 
            <table className="table border shadow">
                <thead>
                    <tr>
                    <th scope="col">#</th>
                    <th scope="col">Account No</th>
                    <th scope="col">Account Status</th>
                    <th scope="col">Account Type</th>
                    <th scope="col">Account Balance</th>
                    <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        accounts.map((account,index)=>(
                            <tr>
                                <th scope="row" key={index}>{index+1}</th>
                                <td>{account.accNo}</td>
                                <td>{account.accStatus}</td>
                                <td>{account.accType}</td>
                                <td>{account.accBal}</td>
                                <td>
                                    <Link 
                                        className="btn btn-primary mx-2"
                                        to={`/deposit/deposit?custNo=${account.custNo}&accNo=${account.accNo}`}
                                    >
                                        Deposit
                                    </Link>
                                    <Link 
                                        className="btn btn-primary mx-2"
                                        to={`/withdraw/withdraw?custNo=${account.custNo}&accNo=${account.accNo}`}
                                    >
                                        Withdraw
                                    </Link>
                                    <Link 
                                        className="btn btn-primary mx-2"
                                        to={`/deactivate/close?custNo=${account.custNo}&accNo==${account.accNo}`}
                                    >
                                        Deactivate
                                    </Link>
                                </td>
                            </tr>
                        ))
                    }
                    
                </tbody>
            </table>
        </div>    
    </div>
  )
}
