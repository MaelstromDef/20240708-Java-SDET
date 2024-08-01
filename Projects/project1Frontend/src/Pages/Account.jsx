import './page.css'
import { baseUrl, UserContext } from '../App';
import { useContext, useState } from 'react';
import axios from 'axios';

// Account shows current account information (company name) and allows information to be changed (company name and password)
export default function Account(){
    const {user, setUser} = useContext(UserContext);
    const [feedback, setFeedback] = useState("");
    const [allowCompanyNameChange, setAllowCompanyNameChange] = useState(true);

    // FORM MODIFICATION

    const chkChangeCompany_Handler = (cb) => {
        setAllowCompanyNameChange(!(cb.target.checked));
    }

    // FORM SUBMISSION

    const handleResponseSuccess = (res) =>{
        // Update context info
        let adminInfo = user.adminInfo;
        adminInfo.id = res.data.id;
        adminInfo.companyName = res.data.adminInfo;

        let newUser = user;
        newUser.adminInfo = adminInfo;

        // Show feedback
        setUser(newUser);
        setFeedback("Account updated.");
    }

    const handleResponseError = (error) =>{
        setFeedback("Something went wrong, please try again.");
    }

    const handleSubmit = (event) =>{
        event.preventDefault();
        setFeedback("");

        // Set up details
        const companyName = event.target.companyName.value.trim();
        const password = event.target.password.value.trim();
        const newAdminInfo = {
            companyName: companyName,
            password: password
        }

        axios.put(baseUrl, newAdminInfo)
        .then(handleResponseSuccess)
        .catch(handleResponseError);
    }

    return (<>
        <h1>Account</h1>
        <form className="VerticalForm" onSubmit={handleSubmit}>
            <label>Company Name</label>
            <div>
                <input type="text" 
                    name="companyName" 
                    defaultValue={user.adminInfo.companyName} 
                    readOnly={allowCompanyNameChange}/>
                <input type="checkbox" 
                    name="chkChangeCompanyName" 
                    defaultValue={false} 
                    onClick={chkChangeCompany_Handler} />
            </div>

            <label>Password</label>
            <input type="password" name="password"/>

            <input type='submit' value='Update Information'/>
        </form>
        <p>{feedback}</p>
    </>);
}