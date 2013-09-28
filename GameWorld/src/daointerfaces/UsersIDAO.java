package daointerfaces;

import java.util.List;

import dto.UsersDTO;

abstract public class UsersIDAO
{
	abstract public void create(UsersDTO row) throws DALException;
	abstract public void delete(int Uid) throws DALException;
	abstract public void update(UsersDTO row) throws DALException;
	abstract public UsersDTO get(int Uid) throws DALException;
	abstract public List<UsersDTO> getList() throws DALException;
}